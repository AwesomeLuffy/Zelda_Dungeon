package Game.Character;

import Game.Animation.GameAnimation;
import Game.Animations;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public interface Character {

    void draw(Graphics graphics) throws SlickException;
    void draw(Graphics graphics, Animations animations) throws SlickException;
    void draw(Graphics graphics, Vector2f vector2f, Animations animations) throws SlickException;
    GameAnimation getAnimation(Animations animations) throws SlickException;

}
