package Game.Character;

import Game.Animation.AnimationManager;
import Game.Character.CharacterPower;
import Game.Character.CharacterWeapon;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

public class Hero extends GameCharacter{

    private final AnimationManager am = AnimationManager.getInstance();
    private CharacterPower characterPower;
    private CharacterWeapon characterWeapon;
    private HashMap<String, Animation> heroAnimation = new HashMap<>();

    public Hero(Builder builder) throws SlickException {
        super(builder);

        this.heroAnimation.put("heroUP", this.am.getAnimationFromName("UP"));
        this.heroAnimation.put("heroDOWN", this.am.getAnimationFromName("DOWN"));
        this.heroAnimation.put("heroLEFT", this.am.getAnimationFromName("LEFT"));
        this.heroAnimation.put("heroRIGHT", this.am.getAnimationFromName("RIGHT"));
    }


    public static Builder builder(){
        return new Builder();
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

    public Animation getHeroAnimation(String name){
        if(this.heroAnimation.containsKey(name)) {
            return this.heroAnimation.get(name);
        }
    return null;
    }


}
