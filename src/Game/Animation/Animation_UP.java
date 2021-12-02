package Game.Animation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class Animation_UP implements Animations{
    private final String name;
    private final int tileSize;

    public Animation_UP(String nameSprite, int _tileSize){
        this.name = nameSprite;
        this.tileSize = _tileSize;
    }

    @Override
    public void constructAnim() throws SlickException {
        Animation animation = new Animation();

        for(int x = 0; x < 8; x++){
            animation.addFrame(Animations.getLinkSprite().getSubImage(
                    x * this.tileSize, 4 * this.tileSize, this.tileSize, this.tileSize), 50);
        }
    }

    @Override
    public void getAnimation() {

    }
}
