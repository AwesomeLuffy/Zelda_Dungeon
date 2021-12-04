package Game.Character;

import Game.Animations;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

public interface Character {

    void draw(Animations animations);
    void draw(Vector2f vector2f, Animations animations);
    Animation getAnimation(Animations animations);

}
