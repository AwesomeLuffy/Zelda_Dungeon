import Game.*;

import Game.Animation.AnimationManager;
import Game.Animation.GameAnimation;
import Game.Animation.Animations;
import Game.Character.CharacterWeapon;
import Game.Character.Hero;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;

public class GameLauncher extends BasicGame{

    private UserInteraction userInteraction;
    private AnimationManager am;
    Animations anims;

    private TiledMap map;

    public GameLauncher(String title) throws SlickException {
        super(title);

    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        //Hero link = Hero.builder().name("Link").build();

        this.am = AnimationManager.getInstance();

        //this.am.loadAnimation();

        try {
            map = new TiledMap("ressources/maps/map.tmx");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        Hero hero = Hero.builder()
                .name("Light Knight")
                .withLife(200)
                .withWeapon(
                CharacterWeapon.builder().withName("Epée de Thanos").withDamage(75).build())
                .build();


    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.render(0,0);
        this.am.getAnimationFromName("UP").draw(60,60);
    }

    public AnimationManager getAnimationManager(){
        return this.am;
    }
}
