import Game.*;

import Game.Animation.AnimationManager;
import Game.Character.CharacterWeapon;
import Game.Character.Colision.GameCollisionManager;
import Game.Character.Type.Hero;
import Game.Character.Type.Enemy;
import Game.Map.GameMap;
import Game.Map.GameMapManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class GameLauncher extends BasicGame{

    private UserInteraction userInteraction;

    private GameMap map1;
    private GameMap map2;
    private GameMap mapAcutely;
    private int state;
    public boolean atKey;

    private AnimationManager am = AnimationManager.getInstance();
    private GameCollisionManager gameCollisionManager = GameCollisionManager.getInstance();

    private Hero hero;
    private Enemy enemy;


    private int delta;

    private boolean justAttack = false;

    private Animations animations;

    public GameLauncher(String title) throws SlickException {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.state = 1;
        this.atKey = false;
        try {
            this.map1 = new GameMap("Map1", "ressources/maps/map1.tmx");
            this.map2 = new GameMap("Map2", "ressources/maps/map2.tmx");
        }catch (Exception e){
            System.out.println(e.toString());
        }

        this.am.load();

        this.hero = Hero.builder()
                .name("Light Knight")
                .withLife(3)
                .withWeapon(
                        CharacterWeapon.builder().withName("Epée de Thanos").withDamage(75).withAnimation("FireEnergy").build())
                .build();
        this.hero.setCharacterPosition(new Vector2f(22,12));

        this.enemy = Enemy.builder().build();
        this.enemy.setCharacterPosition(new Vector2f(32,15));

        this.gameCollisionManager.initCollides();

        GameSound.getInstance().getMusic();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        if(this.delta >= this.hero.getCharacterWeapon().getAnimation().getTotalDuration()){
            this.justAttack = (this.justAttack) ? false : this.justAttack;
            this.delta = 0;
        }

        this.delta+=i;

        if (this.mapAcutely.changeMap(this.hero.getCharacterPosition()) && this.atKey){
            if (this.mapAcutely == this.map1){
                this.state = 2;
                this.hero.setCharacterPosition(new Vector2f(12, 22));
            }
            else if (this.mapAcutely == this.map2){
                this.state = 1;
                this.hero.setCharacterPosition(new Vector2f(12,1));
            }
        }
        if(UserInteraction.isKeyReleased(gameContainer)) {
            if (UserInteraction.isToRightPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToRight(this.hero.getCharacterPosition(), this.atKey)){
                    this.hero.moveToRight(i);
                }
            } else if (UserInteraction.isToLeftPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToLeft(this.hero.getCharacterPosition(), this.atKey)){
                    this.hero.moveToLeft(i);
                }
            } else if (UserInteraction.isToDownPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToDown(this.hero.getCharacterPosition(), this.atKey)){
                    this.hero.moveToDown(i);
                }
            } else if (UserInteraction.isToUpPressed(gameContainer).getKey()) {
                if (this.mapAcutely.canMoveToUp(this.hero.getCharacterPosition(), this.atKey)){
                    this.hero.moveToUp(i);
                }
            }
            else if (UserInteraction.isSpacePressed(gameContainer) && !this.justAttack){
                this.hero.attack(enemy);
                this.justAttack = true;
                this.delta = 0;
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
        if (state == 1){
            this.enemy.setLife(0);//Death of Enemy
            if (!this.enemy.isAlive()){
                if(this.atKey == false){
                    this.am.getGameImage("greyKey").drawImage(new Vector2f(10,15),graphics);
                }
                if (this.hero.getCharacterPosition().getX() == 10 && this.hero.getCharacterPosition().getY() == 15){
                    this.atKey = true;
                }
            }
            else {
                this.enemy.draw(graphics);
            }
            if(this.atKey == false){
                this.am.getGameImage("woodLog").drawImage(new Vector2f(11,0),graphics);
                this.am.getGameImage("woodLog").drawImage(new Vector2f(12,0),graphics);
                this.am.getGameImage("woodLog").drawImage(new Vector2f(13,0),graphics);
            }
        }

        if (this.justAttack) {
             this.hero.getCharacterWeapon().draw(this.hero.getCharacterPosition(), this.hero.getActualDirection());
        }

        this.gameCollisionManager.getGameColision(this.hero).drawRect(graphics);

    }
    public AnimationManager getAnimationManager(){
        return this.am;
    }
}