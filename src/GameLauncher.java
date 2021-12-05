import Game.*;

import Game.Animation.AnimationManager;
import Game.Character.CharacterWeapon;
import Game.Character.Hero;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class GameLauncher extends BasicGame{

    private UserInteraction userInteraction;

    private TiledMap map;

    private AnimationManager am;

    private Hero hero;

    private boolean isKeyJustPressed = false;

    private int inputPressed;

    private Animations animations;

    public GameLauncher(String title) throws SlickException {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        try {
            this.map = new TiledMap("ressources/maps/map1.tmx");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        this.am = AnimationManager.getInstance();
        this.am.loadAnimations();


        this.hero = Hero.builder()
                .name("Light Knight")
                .withLife(200)
                .withWeapon(
                CharacterWeapon.builder().withName("Epée de Thanos").withDamage(75).build())
                .build();

        this.hero.setHeroPosition(new Vector2f(10,10));





    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        if(!gameContainer.getInput().isKeyDown(this.inputPressed)){
            this.isKeyJustPressed = false;
        }

        if(UserInteraction.isToRightPressed(gameContainer) && !isKeyJustPressed){
            this.hero.moveToRight(i);
        }
        else if(UserInteraction.isToLeftPressed(gameContainer) && !isKeyJustPressed){
            this.hero.moveToLeft(i);
        }
        else if(UserInteraction.isToDownPressed(gameContainer) && !isKeyJustPressed){
            this.hero.moveToDown(i);

        }
        else if(UserInteraction.isToUpPressed(gameContainer) && !isKeyJustPressed){
            this.hero.moveToUp(i);
        }

        if(UserInteraction.isAnyKeyDown(gameContainer).getKey()){
            this.isKeyJustPressed = true;
            this.inputPressed = UserInteraction.isAnyKeyDown(gameContainer).getValue();
        }


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.render(0,0);

        this.hero.draw();
    }

    public AnimationManager getAnimationManager(){
        return this.am;
    }
}
