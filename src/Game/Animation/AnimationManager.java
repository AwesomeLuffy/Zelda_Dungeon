package Game.Animation;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {

    private List<Animation> allAnimation = new ArrayList<Animation>();

    private static AnimationManager am;

    private AnimationManager(){

    }

    public static AnimationManager getInstance(){
        if(am == null){
            am = new AnimationManager();
        }
        return am;
    }
}
