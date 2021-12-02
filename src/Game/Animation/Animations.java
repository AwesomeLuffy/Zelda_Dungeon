package Game.Animation;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public interface Animations{
    void constructAnim() throws SlickException;
    void getAnimation();

    static Image getLinkSprite() throws SlickException {
        return new Image("ressources/sprites/linkSprite.jpg");
    }
}
