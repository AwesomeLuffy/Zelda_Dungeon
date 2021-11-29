import Game.*;

import Game.Character.Hero;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{

    UserInteraction userInteraction;

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        Hero link = Hero.builder().name("Link").build();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }
}
