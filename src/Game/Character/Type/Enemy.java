package Game.Character.Type;

import Game.Animation.AnimationManager;
import Game.Animation.GameAnimation;
import Game.Animations;
import Game.Character.Character;
import Game.Character.CharacterWeapon;
import Game.Character.GameCharacter;
import Game.GroupList;
import Game.Map.GameMapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;// <=====
import org.newdawn.slick.geom.Vector2f;

public class Enemy extends GameCharacter implements Character {

    private final AnimationManager am = AnimationManager.getInstance();
    private Animations actualDirection;

    private Enemy(Builder builder) throws SlickException {
        super(builder);
        this.actualDirection = Animations.DOWN;
    }

    public static Builder builder(){
        return new Builder();
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
        this.am.getGroup(GroupList.Enemy).getGameAnimation(animations).play(
                new Vector2f(vector2f.getX(),
                        vector2f.getY())
        );
        //this.drawLife(graphics, vector2f);
        this.drawLife(graphics);
    }

    @Override
    public GameAnimation getAnimation(Animations animations) throws SlickException {
        if(this.am.getGroup(GroupList.Enemy).getGameAnimations().containsKey(animations)){
            return this.am.getGroup(GroupList.Enemy).getGameAnimation(animations);
        }
        return null;
    }

//    private void drawLife(Graphics graphics, Vector2f position){
//        float x = (position.getX() * GameMapManager.getTilesSize()) - 15;
//        float y = (position.getY() * GameMapManager.getTilesSize()) - 20;
//        if (this.isAlive()){
//            graphics.drawString(this.getLife()+"/"+this.getInitialLife(),x,y);
//        }
//    }

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

    public static  class Builder extends GameCharacter.Builder<Builder>{
        private CharacterWeapon characterWeapon;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder withWeapon(CharacterWeapon _characterWeapon){
            this.characterWeapon = _characterWeapon;
            return this;
        }

        public Enemy build() throws SlickException{
            return new Enemy(this);
        }
    }
}