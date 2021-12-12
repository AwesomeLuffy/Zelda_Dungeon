package Game.Character.Type;

import Game.Animation.AnimationManager;
import Game.Animation.GameAnimation;
import Game.Animations;
import Game.Character.Character;
import Game.Character.CharacterPower;
import Game.Character.CharacterWeapon;
import Game.Character.Colision.Collisions;
import Game.Character.Colision.GameCollisionManager;
import Game.Character.GameCharacter;
import Game.GroupList;
import Game.Map.GameMapManager;
import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hero extends GameCharacter implements Character, Collisions {

    private final AnimationManager am = AnimationManager.getInstance();
    private final GameCollisionManager gameColisionManager = GameCollisionManager.getInstance();
    private CharacterPower characterPower;
    private CharacterWeapon characterWeapon;
    private Animations actualDirection;

    public static final Map<Animations, Vector2f> ADDPOS;
    static {
        Map<Animations, Vector2f> tmp = new HashMap<>();
        tmp.put(Animations.UP, new Vector2f(0, -1));
        tmp.put(Animations.DOWN, new Vector2f(0, 1));
        tmp.put(Animations.RIGHT, new Vector2f(1, 0));
        tmp.put(Animations.LEFT, new Vector2f(-1, 0));
        ADDPOS = Collections.unmodifiableMap(tmp);
    }

    private Hero(Builder builder) throws SlickException {
        super(builder);

        this.actualDirection = Animations.DOWN;
        this.characterWeapon = builder.characterWeapon;

        this.gameColisionManager.registerCol(this);

        this.setColide();
    }

    public CharacterWeapon getCharacterWeapon() {
        return characterWeapon;
    }

    public void setCharacterWeapon(CharacterWeapon characterWeapon) {
        this.characterWeapon = characterWeapon;
    }


    public void moveHero(Vector2f addPos, Animations animations, int i){

        this.setCharacterPosition(new Vector2f(
                this.getCharacterPosition().getX() + addPos.getX(),
                this.getCharacterPosition().getY() + addPos.getY())
        );

        this.am.getGroup(GroupList.HERO).getGameAnimation(animations).update(i);

        this.actualDirection = animations;

        this.updateColide();
    }

    public void moveToUp(int i){
        this.moveHero(ADDPOS.get(Animations.UP), Animations.UP, i);
    }

    public void moveToDown(int i){
        this.moveHero(ADDPOS.get(Animations.DOWN), Animations.DOWN, i);
    }

    public void moveToLeft(int i){
        this.moveHero(ADDPOS.get(Animations.LEFT), Animations.LEFT, i);
    }

    public void moveToRight(int i){
        this.moveHero(ADDPOS.get(Animations.RIGHT), Animations.RIGHT, i);
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public void draw(Graphics graphics) throws SlickException{
        this.draw(graphics, this.actualDirection);
    }

    @Override
    public void draw(Graphics graphics, Animations animations) throws SlickException {
        this.draw(graphics, this.getCharacterPosition(), animations);
    }

    @Override
    public void draw(Graphics graphics, Vector2f vector2f, Animations animations) throws SlickException {
        this.am.getGroup(GroupList.HERO).getGameAnimation(animations).play(
                new Vector2f(vector2f.getX(),
                        vector2f.getY())
        );
        this.drawLife(graphics);
    }

    @Override
    public GameAnimation getAnimation(Animations animations) throws SlickException {
        if(this.am.getGroup(GroupList.HERO).getGameAnimations().containsKey(animations)){
            return this.am.getGroup(GroupList.HERO).getGameAnimation(animations);
        }
        return null;
    }



    private void drawLife(Graphics graphics){
        float y = this.am.getGameImage("heartFull").getPosition().y;
        float x = 1;

        if(this.isAlive()) {
            for (int i = 0; i < this.getLife(); i++) {

                if (i % 10 == 0 && i != 0) {
                    y++;
                    x = 1;
                }

                this.am.getGameImage("heartFull").drawImage(
                        new Vector2f(x, y),
                        graphics);
                x++;
            }
        }

        if(this.getLife() != this.getInitialLife()) {
            for (int i = this.getLife(); i < this.getInitialLife(); i++) {

                if (i % 10 == 0 && i != 0 && (this.getLife() % 10 != 0 || this.getLife() % 10 == 0)) {
                    y++;
                    x = 1;
                }

                this.am.getGameImage("heartEmpty").drawImage(
                        new Vector2f(x, y),
                        graphics);
                x++;
            }
        }
    }

    public void attack(GameCharacter gameCharacter){
        if(new Vector2f(this.getCharacterPosition()).add(ADDPOS.get(this.getActualDirection())).equals(gameCharacter.getCharacterPosition())) {
            if (gameCharacter.isAlive()) {
                gameCharacter.setDamage(this.getCharacterWeapon().getDamage());
            }
        }
    }

    public Animations getActualDirection() {
        return actualDirection;
    }

    public void setActualDirection(Animations actualDirection) {
        this.actualDirection = actualDirection;
    }

    @Override
    public void setColide() {
        this.gameColisionManager.addCollideObject(this, this.getCharacterPosition());
    }

    @Override
    public void updateColide() {
        this.gameColisionManager.updateColidePos(this, this.getCharacterPosition());
    }


    public static class Builder extends GameCharacter.Builder<Builder> {
        private CharacterWeapon characterWeapon;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder withWeapon(CharacterWeapon _characterWeapon){
            this.characterWeapon = _characterWeapon;
            return this;
        }

        public Hero build() throws SlickException {
            return new Hero(this);
        }
    }
}
