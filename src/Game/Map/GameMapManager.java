package Game.Map;

import org.newdawn.slick.SlickException;
//import Game.Map.GameMap;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.List;

public class GameMapManager {

    private List<GameMap> allGameMap = new ArrayList<GameMap>();

    private static final int TILES_SIZE = 32;

    private static GameMapManager gmm;

    private GameMapManager() throws SlickException {

    }

    public static GameMapManager getInstance() throws SlickException {

        if(gmm == null){
            gmm = new GameMapManager();
        }

        return gmm;
    }

    public static int getTilesSize(){
        return TILES_SIZE;
    }

//    public TiledMap getMap1(){
//        return this.gameMap1;
//    }
}
