package Game.Animation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class GameAnimation{
    private final int tileSize;
    private final String name;
    private int frameNumber;
    private Animation animation;
    private Image heroSprite;


    public GameAnimation(String _name, int _tileSize, Vector2f vector2f, int _frameNumber) throws SlickException {

        this.name = _name;
        this.tileSize = _tileSize;
        this.frameNumber = _frameNumber;


        this.constructAnim((int) vector2f.getX(), (int) vector2f.getY());
    }

    private void constructAnim(int x, int y) throws SlickException {
        this.animation = new Animation();

        for(int i = x; i < x + this.frameNumber; i++){
            this.animation.addFrame(AnimationManager.getInstance().heroSprite.getSubImage(
                    i * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize), 300);
        }
    }

    public Animation getAnimation(){
        return this.animation;
    }

    public void play(Vector2f vector2f){
        this.animation.draw(vector2f.getX(), vector2f.getY());
    }

    public void update(int i){
        this.animation.update(i);
    }

    @Override
    public String toString(){
        return this.name + " - " + this.animation.toString();
    }
}
