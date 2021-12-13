import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        int largeurScreen = 1440;
        int hauteurScreen = 768;
        boolean isFullscreen = false;

        try {
            AppGameContainer app = new AppGameContainer(new GameLauncher("ZeldaDungeon"));
            app.setDisplayMode(largeurScreen, hauteurScreen, isFullscreen);
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
