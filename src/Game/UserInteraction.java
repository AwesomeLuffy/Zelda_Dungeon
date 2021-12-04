package Game;

import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;

import java.awt.im.InputContext;
import java.util.HashMap;

public abstract class UserInteraction {

    public static int input;
    private static boolean isKeyReleased;


    public static boolean isToUpPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.input = (in.isKeyDown(Input.KEY_UP)) ? Input.KEY_UP : Input.KEY_Z;
        return in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_Z);
    }

    public static boolean isToDownPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.input = (in.isKeyDown(Input.KEY_DOWN)) ? Input.KEY_DOWN : Input.KEY_S;

        return in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S);
    }

    public static boolean isToLeftPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.input = (in.isKeyDown(Input.KEY_LEFT)) ? Input.KEY_LEFT : Input.KEY_Q;

        return in.isKeyDown(Input.KEY_LEFT) || in.isKeyDown(Input.KEY_Q);
    }

    public static boolean isToRightPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.input = (in.isKeyDown(Input.KEY_RIGHT)) ? Input.KEY_RIGHT : Input.KEY_D;
        return in.isKeyDown(Input.KEY_RIGHT) || in.isKeyDown(Input.KEY_D);
    }

    public static boolean isSpacePressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.input = (in.isKeyDown(Input.KEY_RIGHT)) ? Input.KEY_RIGHT : Input.KEY_D;
        return in.isKeyDown(Input.KEY_SPACE);
    }

    public static boolean isKeyReleased(GameContainer gameC){
        if(UserInteraction.input != -1){
            return gameC.getInput().isKeyDown(UserInteraction.input);
        }
        return false;
    }

    public static Pair<Boolean, Integer> isAnyKeyDown(GameContainer gameC){
        if(isToUpPressed(gameC)){
            return new Pair<Boolean, Integer>(
                    true,
                    ((gameC.getInput().isKeyDown(Input.KEY_UP)) ? Input.KEY_UP : Input.KEY_Z));
        }

        else if(isToDownPressed(gameC)){
            return new Pair<Boolean, Integer>(
                    true,
                    ((gameC.getInput().isKeyDown(Input.KEY_DOWN)) ? Input.KEY_DOWN : Input.KEY_S));
        }

        else if(isToLeftPressed(gameC)){
            return new Pair<Boolean, Integer>(
                    true,
                    ((gameC.getInput().isKeyDown(Input.KEY_LEFT)) ? Input.KEY_LEFT : Input.KEY_Q));
        }

        else if(isToRightPressed(gameC)){
            return new Pair<Boolean, Integer>(
                    true,
                    ((gameC.getInput().isKeyDown(Input.KEY_RIGHT)) ? Input.KEY_RIGHT : Input.KEY_D));
        }

        else if(isSpacePressed(gameC)){
            return new Pair<Boolean, Integer>(
                    true,
                    Input.KEY_SPACE);
        }
        else {
            return new Pair<Boolean, Integer>(false, null);
        }
    }

    public static class Pair<T1, T2> {
        private final T1 key;
        private final T2 value;

        public Pair(T1 first, T2 second) {
            this.key = first;
            this.value = second;
        }

        public T1 getKey() {
            return key;
        }

        public T2 getValue() {
            return value;
        }
    }

}
