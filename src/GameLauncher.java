import Game.*;

import Game.Animation.AnimationManager;
import Game.Character.CharacterWeapon;
import Game.Character.Colision.GameCollisionManager;
import Game.Character.Type.Boss;
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
    public boolean atKey;

    private final AnimationManager am = AnimationManager.getInstance();
    private final GameCollisionManager gameCollisionManager = GameCollisionManager.getInstance();

    private Hero hero;
    private Enemy enemy;
    private Boss boss;

    private int delta;
    private int alpha;
    private int gamma;
    private int previousLife;

    private boolean justAttack = false;
    private boolean attackBoss = true;
    private boolean enemyDeath;
    private boolean bossDeath;
    private boolean heroDeath;
    private boolean canAttack;

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
                        CharacterWeapon.builder().withName("Sword of  Thanos").withDamage(75).withAnimation("FireEnergy").build())
                .build();
        this.hero.setCharacterPosition(new Vector2f(22,12));

        this.enemy = Enemy.builder().withLife(100).build();
        this.enemy.setCharacterPosition(new Vector2f(32,15));

        this.gameCollisionManager.initCollides();

        GameSound.getInstance().getMusic();

        this.gamma = 0;
        this.previousLife = this.hero.getLife();
        this.canAttack = true;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(this.delta >= this.hero.getCharacterWeapon().getAnimation().getTotalDuration()){
            this.justAttack =  (this.justAttack) ? false : this.justAttack;
            this.delta = 0;
        }

        if (this.alpha >= 1600) {
            this.attackBoss = false;
            if (this.alpha >= 3200){
                this.alpha = 0;
                this.attackBoss = true;
            }
        }

        this.delta+=i;
        this.alpha+=i;

        if (!this.canAttack){
            this.gamma+=i;
        }

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
                if (this.boss != null){
                    this.hero.attack(boss);
                }
                this.justAttack = true;
                this.delta = 0;
                GameSound.getInstance().getAttack();
            }

            if (this.state == 2 && this.attackBoss && this.boss.isAlive()){
                if (this.canAttack){
                    this.boss.attack(hero);
                    this.gamma = 0;
                }
                if (this.hero.getLife() == this.previousLife - 1){
                    this.previousLife = this.hero.getLife();
                    this.canAttack = false;
                }
                if (this.gamma >= 800){
                    this.canAttack = true;
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

        if (this.hero.isAlive()){
            this.heroDeath = true;
            this.hero.draw(graphics);
        }
        else {
            GameSound.getInstance().getDeath(this.heroDeath);
            this.heroDeath = false;
            System.exit(0);
        }

        if (state == 1){
            if (this.enemy.isAlive()){
                this.enemyDeath = true;
                this.enemy.draw(graphics);
            }
            else {
                if (!this.atKey){
                    this.am.getGameImage("greyKey").drawImage(new Vector2f(10,15),graphics);
                }

                if (this.hero.getCharacterPosition().getX() == 10 && this.hero.getCharacterPosition().getY() == 15){
                    this.atKey = true;
                }

                GameSound.getInstance().getTheWilhelmScream(this.enemyDeath);
                this.enemyDeath = false;
            }

            if(!this.atKey){
                this.am.getGameImage("woodLog").drawImage(new Vector2f(11,0),graphics);
                this.am.getGameImage("woodLog").drawImage(new Vector2f(12,0),graphics);
                this.am.getGameImage("woodLog").drawImage(new Vector2f(13,0),graphics);
            }
        }
        else  {
            if (this.boss == null){
                this.boss = Boss.builder()
                .withLife(500)
                .withWeapon(
                        CharacterWeapon.builder().withName("Dragon Death Sword").withDamage(1).withAnimation("FireEnergy").build()).build();
                this.boss.setCharacterPosition(new Vector2f(29,15));
            }

            if (this.boss.isAlive()){
                this.bossDeath = true;
                this.boss.draw(graphics);
                if (this.attackBoss){
                    this.boss.getCharacterWeapon().draw(this.boss.getCharacterPosition());
                }
            }
            else {
                GameSound.getInstance().getTheWilhelmScream(this.bossDeath);
                this.bossDeath = false;
            }
        }

        if (this.justAttack) {
             this.hero.getCharacterWeapon().draw(this.hero.getCharacterPosition(), this.hero.getActualDirection());
        }
    }

    public AnimationManager getAnimationManager(){
        return this.am;
    }
}