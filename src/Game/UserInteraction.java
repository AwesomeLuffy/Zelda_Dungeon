package Game;

import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;

import java.awt.im.InputContext;

public abstract class UserInteraction {


    public static boolean isToUpPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_Z);
    }

    public static boolean isToDownPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S);
    }

    public static boolean isToLeftPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_LEFT) || in.isKeyDown(Input.KEY_Q);
    }

    public static boolean isToRightPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_RIGHT) || in.isKeyDown(Input.KEY_D);
    }

    public static boolean isSpacePressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_SPACE);
    }

}
