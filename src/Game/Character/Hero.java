package Game.Character;

import Game.Animation.AnimationManager;
import Game.Animation.GameAnimation;
import Game.Animations;
import Game.GroupList;
import Game.Map.GameMapManager;
import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.awt.*;
import java.util.HashMap;

public class Hero extends GameCharacter implements Character{

    private final AnimationManager am = AnimationManager.getInstance();
    private CharacterPower characterPower;
    private CharacterWeapon characterWeapon;
    private HashMap<Animations, Animation> heroAnimation = new HashMap<>();
    private Vector2f heroPosition;
    private Animations actualDirection;

    public Hero(Builder builder) throws SlickException {
        super(builder);

        this.heroPosition = new Vector2f(0, 0);
        this.actualDirection = Animations.DOWN;
    }

    public CharacterWeapon getCharacterWeapon() {
        return characterWeapon;
    }

    public void setCharacterWeapon(CharacterWeapon characterWeapon) {
        this.characterWeapon = characterWeapon;
    }

    public Vector2f getHeroPosition() {
        return heroPosition;
    }

    public void setHeroPosition(Vector2f heroPosition) {
        this.heroPosition = new Vector2f(
                ((int) heroPosition.getX()) ,
                ((int) heroPosition.getY())
        );
    }

    public void moveHero(int x, int y, Animations animations, int i){

        this.setHeroPosition(new Vector2f(
                this.getHeroPosition().getX() + x,
                this.getHeroPosition().getY() + y)
        );

        this.am.getGroup(GroupList.HERO).getGameAnimation(animations).update(i);

        this.actualDirection = animations;

    }

    public void moveToUp(int i){
        this.moveHero(0, -1, Animations.UP, i);
    }

    public void moveToDown(int i){
        this.moveHero(0, 1, Animations.DOWN, i);
    }

    public void moveToLeft(int i){
        this.moveHero(-1, 0, Animations.LEFT, i);
    }

    public void moveToRight(int i){
        this.moveHero(1, 0, Animations.RIGHT, i);
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public void draw() throws SlickException{
        this.draw(this.actualDirection);
    }

    @Override
    public void draw(Animations animations) throws SlickException {
        this.draw(this.heroPosition, animations);
    }

    @Override
    public void draw(Vector2f vector2f, Animations animations) throws SlickException {
        this.am.getGroup(GroupList.HERO).getGameAnimation(animations).play(
                new Vector2f(vector2f.getX() * GameMapManager.getTilesSize(),
                        vector2f.getY() * GameMapManager.getTilesSize())
        );
    }

    @Override
    public GameAnimation getAnimation(Animations animations) throws SlickException {
        if(this.am.getGroup(GroupList.HERO).getGameAnimations().containsKey(animations)){
            return this.am.getGroup(GroupList.HERO).getGameAnimation(animations);
        }
        return null;
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
