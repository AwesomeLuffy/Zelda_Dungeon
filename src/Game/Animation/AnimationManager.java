package Game.Animation;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {

    private List<Animation_UP> allAnimation = new ArrayList<Animation_UP>();

    private Image linkSprite;

    private static AnimationManager am;

    private AnimationManager() throws SlickException {
        this.linkSprite = new Image("ressources/sprites/linkSprite.jpg");
    }

    public static AnimationManager getInstance() throws SlickException {
        if(am == null){
            am = new AnimationManager();
        }
        return am;
    }
}
