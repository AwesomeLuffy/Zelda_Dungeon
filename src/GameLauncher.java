import Game.*;

import Game.Animation.AnimationManager;
import Game.Character.CharacterWeapon;
import Game.Character.Type.Hero;
import Game.Character.Type.Enemy;
import Game.Map.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class GameLauncher extends BasicGame{

    private UserInteraction userInteraction;

    private GameMap map1;
    private GameMap map2;
    private GameMap mapAcutely;
    private int state;

    private Music music;
    //private Vector2f position;

    private AnimationManager am;

    private Hero hero;
    private Enemy enemy;

    private int inputPressed;

    private Animations animations;

    public GameLauncher(String title) throws SlickException {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.state = 1;
        try {
            this.map1 = new GameMap("Map1", "ressources/maps/map1.tmx");
            this.map2 = new GameMap("Map2", "ressources/maps/map2.tmx");
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

        this.enemy = Enemy.builder().build();
        this.enemy.setCharacterPosition(new Vector2f(32,15));

        this.music = GameSound.getInstance().getMusic();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (this.mapAcutely.changeMap(this.hero.getCharacterPosition())){
            if (this.mapAcutely == this.map1){
                this.state = 2;
                this.hero.setCharacterPosition(new Vector2f(13, 22));
            }
            else if (this.mapAcutely == this.map2){
                this.state = 1;
                this.hero.setCharacterPosition(new Vector2f(13,1));
            }
        }
        if(UserInteraction.isKeyReleased(gameContainer)) {
            if (UserInteraction.isToRightPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToRight(this.hero.getCharacterPosition())){
                    this.hero.moveToRight(i);
                }
            } else if (UserInteraction.isToLeftPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToLeft(this.hero.getCharacterPosition())){
                    this.hero.moveToLeft(i);
                }
            } else if (UserInteraction.isToDownPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToDown(this.hero.getCharacterPosition())){
                    this.hero.moveToDown(i);
                }
            } else if (UserInteraction.isToUpPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToUp(this.hero.getCharacterPosition())){
                    this.hero.moveToUp(i);
                }
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (state == 1){
            this.mapAcutely = this.map1;
        }
        else {
            this.mapAcutely = this.map2;
        }
        this.mapAcutely.render();
        this.hero.draw(graphics);
        this.enemy.draw(graphics);
    }

    public AnimationManager getAnimationManager(){
        return this.am;
    }
}