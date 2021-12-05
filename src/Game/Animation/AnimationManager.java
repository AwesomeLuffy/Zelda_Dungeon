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
    private HashMap<String, GameImage> allImage = new HashMap<>();

    protected Image heroSprite;
    protected Image heartSpriteFull;
    protected Image getHeartSpriteEmpty;

    private static AnimationManager am;


    private AnimationManager() throws SlickException {

    }

    public static AnimationManager getInstance() throws SlickException {
        if(am == null){
            am = new AnimationManager();
        }
        return am;
    }

    public void load() throws SlickException {

        this.heroSprite = new Image("ressources/sprites/heroSprite.png");


        this.allAnimGroup.put(GroupList.HERO, new AnimationGroup(GroupList.HERO, 0, 6, 3));
        this.allAnimGroup.put(GroupList.Enemy, new AnimationGroup(GroupList.Enemy, 4, 3, 3));
        this.allAnimGroup.put(GroupList.Boss, new AnimationGroup(GroupList.Enemy, 0, 3, 3));

        this.allImage.put("heartFull", new GameImage("heartFull", new Image("ressources/sprites/heart32_32_FULL.png")));
        this.allImage.put("heartEmpty", new GameImage("heartFull", new Image("ressources/sprites/heart32_32_EMPTY.png")));
    }

    public AnimationGroup getGroup(GroupList groupList){
        return this.allAnimGroup.get(groupList);
    }

    public GameImage getGameImage(String name){
        if(this.allImage.containsKey(name)){
            return this.allImage.get(name);
        }
        return null;
    }
}
