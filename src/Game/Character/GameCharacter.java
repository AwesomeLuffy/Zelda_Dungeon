package Game.Character;

public class GameCharacter {

    private final String name;
    private int life;


    protected GameCharacter(Builder<?> gameCharacterBuilder){
        this.name = gameCharacterBuilder.name;
        this.life = gameCharacterBuilder.life;
    }

    public int getLife(){
        return this.life;
    }

    public void setLife(int life){
        this.life = life;
    }

    public String getName(){
        return this.name;
    }

    public boolean isAlive(){
        return this.life > 0;
    }

    public static Builder builder(){
        return new Builder(){
            @Override
            public Builder getThis(){
                return this;
            }
        };
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String name;
        private int life = 100;

        public abstract T getThis();

        public T name(String name){
            this.name = name;
            return this.getThis();
        }

        public T withLife(int life){
            this.life = life;
            return this.getThis();
        }

        public GameCharacter build(){
            return new GameCharacter(this);
        }
    }
}
