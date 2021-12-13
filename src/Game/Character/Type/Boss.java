package Game.Character.Type;

import Game.Animation.AnimationManager;
import Game.Animation.GameAnimation;
import Game.Animations;
import Game.Character.Character;
import Game.Character.CharacterWeapon;
import Game.Character.GameCharacter;
import Game.GameSound;
import Game.GroupList;
import Game.Map.GameMapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Boss  extends GameCharacter implements Character {
    private final AnimationManager am = AnimationManager.getInstance();
    private Animations actualDirection;
    private CharacterWeapon characterWeapon;

    private Boss(Boss.Builder builder) throws SlickException {
        super(builder);
        this.actualDirection = Animations.DOWN;
        this.characterWeapon = builder.characterWeapon;
    }

    public CharacterWeapon getCharacterWeapon() {
        return characterWeapon;
    }

    public static Boss.Builder builder(){
        return new Boss.Builder();
    }

    @Override
    public void draw(Graphics graphics) throws SlickException {
        this.draw(graphics, this.actualDirection);
    }

    @Override
    public void draw(Graphics graphics, Animations animations) throws SlickException {
        this.draw(graphics, this.getCharacterPosition(), animations);
    }

    @Override
    public void draw(Graphics graphics, Vector2f vector2f, Animations animations) throws SlickException {
        this.am.getGroup(GroupList.Boss).getGameAnimation(animations).play(
                new Vector2f(vector2f.getX(),
                        vector2f.getY())
        );
        this.drawLife(graphics);
    }

    @Override
    public GameAnimation getAnimation(Animations animations) throws SlickException {
        if(this.am.getGroup(GroupList.Boss).getGameAnimations().containsKey(animations)){
            return this.am.getGroup(GroupList.Boss).getGameAnimation(animations);
        }
        return null;
    }

    private void drawLife(Graphics graphics){
        if(this.isAlive()){
            String chars = Integer.toString(this.getLife()) + "/" + this.getInitialLife();

            Rectangle rectangle = new Rectangle(
                    this.getCharacterPosition().getX() * GameMapManager.getTilesSize(), (this.getCharacterPosition().getY() - 1 ) * GameMapManager.getTilesSize(),
                    GameMapManager.getTilesSize(), GameMapManager.getTilesSize());

            graphics.drawString(chars,
                    (rectangle.getX() + rectangle.getWidth() / 2) - (graphics.getFont().getWidth(chars) / 2),
                    (rectangle.getY() + rectangle.getHeight() / 2) - (graphics.getFont().getHeight(chars) / 2) );
        }
    }

    public void attack(GameCharacter gameCharacter) throws SlickException {
        for(Animations animations : Animations.values()) {
            if (new Vector2f(this.getCharacterPosition()).add(Hero.ADDPOS.get(animations)).equals(gameCharacter.getCharacterPosition())) {
                if (gameCharacter.isAlive()) {
                    GameSound.getInstance().getSelfDamage();
                    gameCharacter.setDamage(this.getCharacterWeapon().getDamage());
                }
            }
        }
    }

    public Animations getActualDirection() {
        return actualDirection;
    }

    public static  class Builder extends GameCharacter.Builder<Boss.Builder>{
        private CharacterWeapon characterWeapon;

        @Override
        public Boss.Builder getThis(){
            return this;
        }

        public Boss.Builder withWeapon(CharacterWeapon _characterWeapon){
            this.characterWeapon = _characterWeapon;
            return this;
        }

        public Boss build() throws SlickException{
            return new Boss(this);
        }
    }
}
