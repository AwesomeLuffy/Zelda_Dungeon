package Game.Character;

public class CharacterWeapon {
    private final String name;
    private int damage;
    private final boolean isProjectile;

    private CharacterWeapon(WeaponBuilder weaponBuilder){
        this.name = weaponBuilder.name;
        this.damage = weaponBuilder.damage;
        this.isProjectile = weaponBuilder.isProjectile;
    }

    public static WeaponBuilder builder(){
        return new WeaponBuilder();
    }

    public static class WeaponBuilder{
        private String name;
        private int damage;
        private boolean isProjectile;

        public WeaponBuilder(){
            this.name = "Super Arme";
            this.damage = 20;
            this.isProjectile = false;
        }

        public WeaponBuilder withName(String _name){
            this.name = _name;
            return this;
        }

        public WeaponBuilder withDamage(int _damage){
            this.damage = _damage;
            return this;
        }

        public WeaponBuilder isProjectile(){
            this.isProjectile = true;
            return this;
        }

        public CharacterWeapon build(){
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
}
