package Game.Character;

import Game.Animation.AnimationManager;
import Game.Animations;
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

    public Hero(Builder builder) throws SlickException {
        super(builder);

        this.heroAnimation.put(Animations.UP, this.am.getAnimationFromName("UP"));
        this.heroAnimation.put(Animations.DOWN, this.am.getAnimationFromName("DOWN"));
        this.heroAnimation.put(Animations.LEFT, this.am.getAnimationFromName("LEFT"));
        this.heroAnimation.put(Animations.RIGHT, this.am.getAnimationFromName("RIGHT"));

        this.heroPosition = new Vector2f(0, 0);
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

    public void moveHero(int x, int y){
        this.setHeroPosition(new Vector2f(
                this.getHeroPosition().getX() + x,
                this.getHeroPosition().getY() + y)
        );
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public void draw(Animations animations) {
        this.getAnimation(animations).draw(
                this.getHeroPosition().getX() * GameMapManager.getTilesSize(),
                this.getHeroPosition().getY() * GameMapManager.getTilesSize()
        );
    }

    @Override
    public void draw(Vector2f vector2f, Animations animations){
        this.getAnimation(animations).draw(vector2f.getX(), vector2f.getY());
    }

    @Override
    public Animation getAnimation(Animations animations) {
        if(this.heroAnimation.containsKey(animations)) {
            return this.heroAnimation.get(animations);
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
