import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        try {
            AppGameContainer appGameContainer = new AppGameContainer(new Game("Zelda Dungeon"));
        } catch (SlickException e) {
            System.out.println();
        }

        System.out.println("prout");
    }
    
}
