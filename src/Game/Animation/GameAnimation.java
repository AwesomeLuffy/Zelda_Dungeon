package Game.Animation;

import Game.Map.GameMapManager;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class GameAnimation{
    private final int tileSize;
    private final int duration;
    private final int frameNumber;

    private final boolean isVertical;

    private final String name;

    private Animation animation;

    private Image sprite;

    public static GameAnimationBuilder builder(){
        return new GameAnimationBuilder();
    }

    private GameAnimation(GameAnimationBuilder builder) throws SlickException {

        this.name = builder.name;
        this.tileSize = builder.tileSize;
        this.frameNumber = builder.frameNumber;
        this.sprite = builder.sprite;
        this.isVertical = builder.isVertical;
        Vector2f spritePos = builder.spritePos;
        this.duration = builder.duration;


        this.constructAnim((int) spritePos.getX(), (int) spritePos.getY(), this.isVertical);
    }

    private void constructAnim(int x, int y, boolean isVertical) throws SlickException {
        this.animation = new Animation();

        for(int i = x; i < x + this.frameNumber; i++){
            if(isVertical) {
                this.animation.addFrame(this.sprite.getSubImage(
                        y * this.tileSize, i * this.tileSize, this.tileSize, this.tileSize), this.duration);
            }
            else {
                this.animation.addFrame(this.sprite.getSubImage(
                        i * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize), this.duration);
            }
        }
    }



    public Animation getAnimation(){
        return this.animation;
    }

    public int getTotalDuration(){
        return this.frameNumber * this.duration;
    }

    public void play(Vector2f vector2f){
        this.animation.draw(vector2f.getX() * GameMapManager.getTilesSize(), vector2f.getY() * GameMapManager.getTilesSize());
    }

    public void update(int i){
        this.animation.update(i);
    }

    @Override
    public String toString(){
        return this.name + " - " + this.animation.toString();
    }

    public static class GameAnimationBuilder{

        private int tileSize;
        private int frameNumber;
        private int duration;
        private boolean isVertical;
        private Animation animation;
        private Image sprite;
        private String name;
        private Vector2f spritePos;

        public GameAnimationBuilder(){
            this.tileSize = 32;
            this.name = "";
            this.frameNumber = 1;
            this.sprite = null;
            this.isVertical = false;
            this.duration = 300;
        }

        public GameAnimationBuilder withName(String _name){
            this.name = _name;
            return this;
        }

        public GameAnimationBuilder withFrameNumber(int _number){
            this.frameNumber = _number;
            return this;
        }

        public GameAnimationBuilder withTileSize(int _number){
            this.tileSize = 32;
            return this;
        }

        public GameAnimationBuilder withSprite(Image _img){
            this.sprite = _img;
            return this;
        }

        public GameAnimationBuilder isVertical(){
            this.isVertical = true;
            return this;
        }

        public GameAnimationBuilder withDuration(int _duration){
            this.duration = _duration;
            return this;
        }

        public GameAnimationBuilder withSpritePos(Vector2f vector2f){
            this.spritePos = vector2f;
            return this;
        }

        public GameAnimation build() throws SlickException {
            return new GameAnimation(this);
        }

    }
}
