package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import Game.Utils.Pair;

public abstract class UserInteraction {

    private static int lastKeyPressed;

//    public static class Pair<T1, T2> {
//        private final T1 key;
//        private final T2 value;
//
//        public Pair(T1 first, T2 second) {
//            this.key = first;
//            this.value = second;
//        }
//
//        public T1 getKey() {
//            return key;
//        }
//
//        public T2 getValue() {
//            return value;
//        }
//    }


    public static Pair<Boolean, Integer> isToUpPressed(GameContainer gameC){
        Input in = gameC.getInput();

        UserInteraction.lastKeyPressed = (in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_Z)) ?
                (in.isKeyDown(Input.KEY_UP)) ? Input.KEY_UP : Input.KEY_Z : -1;

        return new Pair<Boolean, Integer>((in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_Z)),
                lastKeyPressed);
    }

    public static Pair<Boolean, Integer> isToDownPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.lastKeyPressed = (in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S)) ?
                (in.isKeyDown(Input.KEY_DOWN)) ? Input.KEY_DOWN : Input.KEY_S : -1;

        return new Pair<Boolean, Integer>((in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S)),
                lastKeyPressed);
    }

    public static Pair<Boolean, Integer> isToLeftPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.lastKeyPressed = (in.isKeyDown(Input.KEY_LEFT) || in.isKeyDown(Input.KEY_Q)) ?
                (in.isKeyDown(Input.KEY_LEFT)) ? Input.KEY_LEFT : Input.KEY_Q : -1;
        return new Pair<Boolean, Integer>((in.isKeyDown(Input.KEY_LEFT) || in.isKeyDown(Input.KEY_Q)),
                lastKeyPressed);
    }

    public static Pair<Boolean, Integer> isToRightPressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.lastKeyPressed = (in.isKeyDown(Input.KEY_RIGHT) || in.isKeyDown(Input.KEY_D)) ?
                (in.isKeyDown(Input.KEY_RIGHT)) ? Input.KEY_RIGHT : Input.KEY_D : -1;

        return new Pair<Boolean, Integer>((in.isKeyDown(Input.KEY_RIGHT) || in.isKeyDown(Input.KEY_D)),
                lastKeyPressed);
    }

    public static boolean isSpacePressed(GameContainer gameC){
        Input in = gameC.getInput();
        UserInteraction.lastKeyPressed = (in.isKeyDown(Input.KEY_SPACE)) ?
                Input.KEY_SPACE : -1;
        return in.isKeyDown(Input.KEY_SPACE);
    }

    public static boolean isKeyReleased(GameContainer gameC){
        if(UserInteraction.lastKeyPressed != -1){
            return !gameC.getInput().isKeyDown(UserInteraction.lastKeyPressed);
        }
        else {
            return true;
        }

    }

    public static Pair<Boolean, Integer> isAnyKeyDown(GameContainer gameC){
        if(isToRightPressed(gameC).getKey()){
            return isToRightPressed(gameC);
        }
        else if(isToLeftPressed(gameC).getKey()){
            return isToLeftPressed(gameC);
        }
        else if(isToUpPressed(gameC).getKey()){
            return isToUpPressed(gameC);
        }
        else if(isToDownPressed(gameC).getKey()){
            return isToDownPressed(gameC);
        }
        else if(isSpacePressed(gameC)){
            return new Pair<Boolean, Integer>(true, Input.KEY_SPACE);
        }

        return new Pair<Boolean, Integer>(false, null);
    }



}