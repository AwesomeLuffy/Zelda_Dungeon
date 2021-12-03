package Game.Animation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimationManager {

    private HashMap<String, Animation> allAnim = new HashMap<String, Animation>();

    protected Image heroSprite;

    private static AnimationManager am;


    private AnimationManager() throws SlickException {
        this.heroSprite = new Image("ressources/sprites/heroSprite.png");
    }

    public static AnimationManager getInstance() throws SlickException {
        if(am == null){
            am = new AnimationManager();
        }
        return am;
    }

    public void loadAnimation() throws SlickException {
        this.allAnim.put("UP", new GameAnimation("UP", 32, new Point(6, 0)).getAnimation());
        this.allAnim.put("DOWN", new GameAnimation("DOWN", 32, new Point(6, 1)).getAnimation());
        this.allAnim.put("LEFT", new GameAnimation("LEFT", 32, new Point(6, 2)).getAnimation());
        this.allAnim.put("RIGHT", new GameAnimation("RIGHT", 32, new Point(6, 3)).getAnimation());
    }

    public Animation getAnimationFromName(String name) throws SlickException {
        if(allAnim.containsKey(name)){
            return allAnim.get(name);
        }
        return null;
    }
}
