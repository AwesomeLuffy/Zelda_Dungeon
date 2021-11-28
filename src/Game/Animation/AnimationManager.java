package Game.Animation;

public class AnimationManager {

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
