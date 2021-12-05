package Game.Character;

import Game.Animation.GameAnimation;
import Game.Animations;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public interface Character {

    void draw() throws SlickException;
    void draw(Animations animations) throws SlickException;
    void draw(Vector2f vector2f, Animations animations) throws SlickException;
    GameAnimation getAnimation(Animations animations) throws SlickException;

}
