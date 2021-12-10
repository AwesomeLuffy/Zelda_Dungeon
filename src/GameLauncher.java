import Game.*;

import Game.Animation.AnimationManager;
import Game.Character.CharacterWeapon;
import Game.Character.Type.Hero;
import Game.Map.GameMap;
import Game.Map.GameMapManager;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class GameLauncher extends BasicGame{

    private UserInteraction userInteraction;

    private GameMap map;
    //private GameMap map2;

    private AnimationManager am;

    private Hero hero;

    private int inputPressed;

    private Animations animations;

    public GameLauncher(String title) throws SlickException {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        try {
            this.map = new GameMap("Map2", "ressources/maps/map.tmx");
            //this.map = new TiledMap("ressources/maps/map1.tmx");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        this.am = AnimationManager.getInstance();
        this.am.load();

        this.hero = Hero.builder()
                .name("Light Knight")
                .withLife(3)
                .withWeapon(
                        CharacterWeapon.builder().withName("Epée de Thanos").withDamage(75).build())
                .build();
        this.hero.setCharacterPosition(new Vector2f(22,12));
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        if(UserInteraction.isKeyReleased(gameContainer)) {
            if (UserInteraction.isToRightPressed(gameContainer).getKey()) {
                if (this.map.canMoveToRight(this.hero.getCharacterPosition())){
                    this.hero.moveToRight(i);
                }
            } else if (UserInteraction.isToLeftPressed(gameContainer).getKey()) {
                if (this.map.canMoveToLeft(this.hero.getCharacterPosition())){
                    this.hero.moveToLeft(i);
                }
            } else if (UserInteraction.isToDownPressed(gameContainer).getKey()) {
                if (this.map.canMoveToDown(this.hero.getCharacterPosition())){
                    this.hero.moveToDown(i);
                }
            } else if (UserInteraction.isToUpPressed(gameContainer).getKey()) {
                if (this.map.canMoveToUp(this.hero.getCharacterPosition())){
                    this.hero.moveToUp(i);
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.render();
        this.hero.draw(graphics);
    }

    public AnimationManager getAnimationManager(){
        return this.am;
    }
}