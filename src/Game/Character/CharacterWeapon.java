package Game.Character;

import Game.Animation.AnimationManager;
import Game.Animation.GameAnimation;
import Game.Animations;
import Game.Character.Type.Boss;
import Game.Character.Type.Hero;
import Game.GroupList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.util.Objects;

public class CharacterWeapon {
    private final String name;
    private static AnimationManager am;
    private int damage;
    private final boolean isProjectile;
    private String animation;

    static {
        try {
            am = AnimationManager.getInstance();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    private CharacterWeapon(WeaponBuilder weaponBuilder) throws SlickException {
        this.name = weaponBuilder.name;
        this.damage = weaponBuilder.damage;
        this.isProjectile = weaponBuilder.isProjectile;
        this.animation = weaponBuilder.animation;
    }

    public static WeaponBuilder builder(){
        return new WeaponBuilder();
    }

    public static class WeaponBuilder{
        private String name;
        private int damage;
        private boolean isProjectile;
        private String animation;

        public WeaponBuilder(){
            this.name = "Super Arme";
            this.damage = 20;
            this.isProjectile = false;
            this.animation = "";
        }

        public WeaponBuilder withName(String _name){
            this.name = _name;
            return this;
        }

        public WeaponBuilder withDamage(int _damage){
            this.damage = _damage;
            return this;
        }

        public WeaponBuilder setProjectile(){
            this.isProjectile = true;
            return this;
        }

        public WeaponBuilder withAnimation(String animationName){
            this.animation = animationName;
            return this;
        }

        public CharacterWeapon build() throws SlickException {
            return new CharacterWeapon(this);
        }
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isProjectile() {
        return isProjectile;
    }

    public GameAnimation getAnimation(){
        return (!this.animation.equals("")) ? am.getUniqueAnimation(this.animation) : null;
    }

    public void draw(Vector2f position, Animations direction){
        this.getAnimation().play(
                position.add(Hero.ADDPOS.get(direction)));
    }

    public void draw(Vector2f position){
        Vector2f vector2f = position.copy();
        this.getAnimation().play(position.add(Hero.ADDPOS.get(Animations.UP)));
        position.add(Hero.ADDPOS.get(Animations.UP).negate());
        this.getAnimation().play(position.add(Hero.ADDPOS.get(Animations.DOWN)));
        position.add(Hero.ADDPOS.get(Animations.DOWN).negate());
        this.getAnimation().play(position.add(Hero.ADDPOS.get(Animations.RIGHT)));
        position.add(Hero.ADDPOS.get(Animations.RIGHT).negate());
        this.getAnimation().play(position.add(Hero.ADDPOS.get(Animations.LEFT)));
        position.add(Hero.ADDPOS.get(Animations.LEFT).negate());

    }
}
