package Game;

import org.jetbrains.annotations.NotNull;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import java.awt.im.InputContext;

public abstract class UserInteraction {

    public boolean isToUpPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_UP) || in.isKeyDown(Input.KEY_Z);
    }

    public boolean isToDownPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_DOWN) || in.isKeyDown(Input.KEY_S);
    }

    public boolean isToLeftPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_LEFT) || in.isKeyDown(Input.KEY_Q);
    }

    public boolean isToRightPressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_RIGHT) || in.isKeyDown(Input.KEY_D);
    }

    public boolean isSpacePressed(@NotNull GameContainer gameC){
        Input in = gameC.getInput();
        return in.isKeyDown(Input.KEY_SPACE);
    }

}
