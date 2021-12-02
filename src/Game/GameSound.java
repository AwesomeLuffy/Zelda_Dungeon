package Game;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
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

    public void setNewRandom() {
        Random rand = new Random();
        chance = (rand.nextInt(4));
    }

    private GameSound() throws SlickException {
        this.selfDamage = new Sound("./ressources/audio/Oh_no.wav");
        switch(chance){
            case 1: this.attack = new Sound("./ressources/audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack1.wav");
                break;
            case 2: this.attack = new Sound("./ressources/audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack2.wav");
                break;
            case 3: this.attack = new Sound("./ressources/audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack3.wav");
                break;
            case 4: this.attack = new Sound("./ressources/audio/LinkAttack/AdultLinkAttack/OOT_AdultLink_Attack4.wav");
                break;
        }
        this.enemyDamage = new Sound("./ressources/audio/Oh_no.wav");
        this.bow = new Sound("./ressources/audio/Oh_no.wav");
        this.music = new Music("./ressources/audio/Arcade-Fantasy.mp3");
        this.death = new Sound("./ressources/audio/Death_sound.mp3");
    }
    public static GameSound getInstance() throws SlickException {
        if (singleInstance == null)
            singleInstance = new GameSound();
        return singleInstance;
    }

    public Sound getSelfDamage() throws SlickException{
        return selfDamage;
    }

    public Sound getAttack() throws SlickException{
        return attack;
    }

    public Sound getEnemyDamage() throws SlickException{
        return enemyDamage;
    }

    public Sound getBow() throws SlickException{
        return bow;
    }

    public Music getMusic() throws SlickException {
        return music;
    }

    public Sound getDeath() throws SlickException{
        return death;
    }
}