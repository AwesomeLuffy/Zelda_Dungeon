package Game;

import org.newdawn.slick.*;
import java.util.Random;

public class GameSound {

    private Sound selfDamage;
    private Sound attack;
    private Sound enemyDamage;
    private Sound bow;
    private Music music;
    private Sound death;

    private static GameSound singleInstance = null;
    private int chance;

    private GameSound() throws SlickException {
        this.selfDamage = new Sound("./audio/Oh_no.wav");;
        this.attack = attack;
        this.enemyDamage = enemyDamage;
        this.bow = bow;
        this.music = music;
        this.death = death;
    }
    public static GameSound getInstance() throws SlickException {
        if (singleInstance == null)
            singleInstance = new GameSound();

        return singleInstance;
    }

    public Sound getSelfDamage() throws SlickException{
        return selfDamage;
    }

    public void setNewRandom() {
        Random rand = new Random();
        chance = (rand.nextInt(4));
    }
    public Sound getAttack() throws SlickException{
        switch(chance){
            case 1: attack = new Sound("./audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack1.wav");
                break;
            case 2: attack = new Sound("./audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack2.wav");
                break;
            case 3: attack = new Sound("./audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack3.wav");
                break;
            case 4: attack = new Sound("./audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack4.wav");
                break;
        }
        attack = new Sound("./audio/Oh_no.wav");
        return attack;
    }

    public Sound getEnemyDamage() throws SlickException{
        return enemyDamage;
    }

    public Sound getBow() throws SlickException{
        bow = new Sound("./audio/Oh_no.wav");
        return bow;
    }

    public Music getMusic() throws SlickException {
        music = new Music("./audio/Arcade-Fantasy.mp3");
        return music;
    }

    public Sound getDeath() throws SlickException{
        death = new Sound("./audio/Death_sound.mp3");
        return death;
    }
}