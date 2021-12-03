package Game.Animation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public interface Animations{

    static Image getHeroSprite() throws SlickException {
        return new Image("ressources/sprites/heroSprite.png");
    }

}
