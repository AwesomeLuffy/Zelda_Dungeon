package Game.Animation;

import Game.Animations;
import Game.GroupList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnimationManager {

    private HashMap<GroupList, AnimationGroup> allAnimGroup = new HashMap<>();

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

    public void loadAnimations() throws SlickException {
        this.allAnimGroup.put(GroupList.HERO, new AnimationGroup(GroupList.HERO, 0, 6, 3));
        this.allAnimGroup.put(GroupList.Enemy, new AnimationGroup(GroupList.Enemy, 4, 3, 3));
    }

    public AnimationGroup getGroup(GroupList groupList){
        return this.allAnimGroup.get(groupList);
    }
}
