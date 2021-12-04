package Game.Animation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class GameAnimation{
    private final int tileSize;
    private final String name;
    private Animation animation;
    private Image heroSprite;


    public GameAnimation(String _name, int _tileSize, Point point) throws SlickException {

        this.name = _name;
        this.tileSize = _tileSize;

        this.heroSprite = AnimationManager.getInstance().heroSprite;


        this.constructAnim((int) point.getX(), (int) point.getY());
    }

    private void constructAnim(int x, int y) throws SlickException {
        this.animation = new Animation();

        for(int i = x; i < x+3; i++){
            this.animation.addFrame(this.heroSprite.getSubImage(
                    i * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize), 300);
        }
    }

    public Animation getAnimation(){
        return this.animation;
    }
}
