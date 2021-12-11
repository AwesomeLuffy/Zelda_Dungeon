package Game;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import java.util.Random;

public class GameSound {

    private final Sound selfDamage;
    private final Sound attack;
    private final Sound enemyDamage;
    private final Music music;
    private final Sound death;

    private static GameSound singleInstance = null;


    private GameSound() throws SlickException {
        this.selfDamage = new Sound("ressources/audio/Oh_no.wav");
        this.attack = new Sound("ressources/audio/short-fireball-woosh-6146.wav");
        this.enemyDamage = new Sound("ressources/audio/Minecraft Oof.wav");
        this.music = new Music("ressources/audio/Arcade-Fantasy.wav");
        this.death = new Sound("ressources/audio/Death_sound.wav");
    }
    public static GameSound getInstance() throws SlickException {
        if (singleInstance == null)
            singleInstance = new GameSound();
        return singleInstance;
    }

    public Sound getSelfDamage() throws SlickException{
        selfDamage.play(1.0f, 0.025f);
        return selfDamage;
    }

    public Sound getAttack() throws SlickException{
        attack.play(1.0f,0.05f);
        return attack;
    }

    public Sound getEnemyDamage() throws SlickException{
        enemyDamage.play(1.0f,0.2f);
        return enemyDamage;
    }

    public Music getMusic() throws SlickException {
        music.loop();
        music.setVolume(0.02f);
        return music;
    }

    public Sound getDeath() throws SlickException{
        death.play(1.0f,0.05f);
        return death;
    }
}