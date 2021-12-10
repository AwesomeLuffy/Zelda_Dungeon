package Game.Animation;

import Game.GroupList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;

public class AnimationManager {

    private HashMap<GroupList, AnimationGroup> allAnimGroup = new HashMap<>();
    private HashMap<String, GameImage> allImage = new HashMap<>();
    private HashMap<String, GameAnimation> uniqueAnim = new HashMap<>();

    protected Image heroSprite;
    protected Image bulletSprite;
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
        this.bulletSprite = new Image("ressources/sprites/bulletSprite.png");


        this.allAnimGroup.put(GroupList.HERO, new AnimationGroup(this.heroSprite, GroupList.HERO, 0, 6, 3, false));
        this.allAnimGroup.put(GroupList.Enemy, new AnimationGroup(this.heroSprite, GroupList.Enemy, 4, 3, 3, false));
        this.allAnimGroup.put(GroupList.Boss, new AnimationGroup(this.heroSprite, GroupList.Enemy, 0, 3, 3, false));

        this.uniqueAnim.put("Bullet", GameAnimation.builder()
                .withSprite(this.bulletSprite)
                .withSpritePos(new Vector2f(0, 6))
                .withDuration(100)
                .withTileSize(32)
                .withFrameNumber(4)
                .withName("Bullet")
                .isVertical()
                .build());

        this.allImage.put("heartFull", new GameImage("heartFull", new Image("ressources/sprites/heart32_32_FULL.png")));
        this.allImage.put("heartEmpty", new GameImage("heartEmpty", new Image("ressources/sprites/heart32_32_EMPTY.png")));
    }

    public AnimationGroup getGroup(GroupList groupList){
        return this.allAnimGroup.get(groupList);
    }

    public GameAnimation getUniqueAnimation(String name){
        if(this.uniqueAnim.containsKey(name)) {
            return this.uniqueAnim.get(name);
        }
        return null;
    }

    public GameImage getGameImage(String name){
        if(this.allImage.containsKey(name)){
            return this.allImage.get(name);
        }
        return null;
    }
}