package Game.Character;

import Game.Character.CharacterPower;
import Game.Character.CharacterWeapon;

public class Hero extends GameCharacter{

    private CharacterPower characterPower;
    private CharacterWeapon characterWeapon;

    public Hero(Builder builder){
        super(builder);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GameCharacter.Builder<Builder> {

        @Override
        public Builder getThis(){
            return this;
        }

        public Hero build(){
            return new Hero(this);
        }
    }


}
