package Game.Animation;

import Game.Animations;
import Game.GroupList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;
import java.util.Map;

public class AnimationGroup {

    private HashMap<Animations, GameAnimation> animationGroup = new HashMap<>();
    private GroupList groupList;

    public AnimationGroup(Image sprite, GroupList _groupList, int row, int column, int frameNumber, boolean isVertical) throws SlickException {

        this.groupList = _groupList;

        int i = row;
        for(Animations animation : Animations.values()) {
            this.animationGroup.put(animation,
                    GameAnimation.builder()
                            .withSprite(sprite)
                            .withFrameNumber(frameNumber)
                            .withName(animation.toString())
                            .withSpritePos(new Vector2f(column, i))
                            .build());
            i++;
        }

    }

    public HashMap<Animations, GameAnimation> getGameAnimations(){
        return this.animationGroup;
    }

    public GameAnimation getGameAnimation(Animations animations){
        if(this.animationGroup.containsKey(animations)){
            return this.animationGroup.get(animations);
        }
        return null;
    }

    @Override
    public String toString(){
        String temp = "";
        for(Map.Entry<Animations, GameAnimation> entry : this.animationGroup.entrySet()){
            temp += entry.getKey().toString() + " : " + entry.getValue().toString() + "\n";
        }
        return temp;
    }


}
