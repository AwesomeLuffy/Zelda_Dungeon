package Game.Map;

import java.util.ArrayList;
import java.util.List;

public class GameMapManager {

    private List<Map> allMap = new ArrayList<Map>();

    private static final int TILES_SIZE = 32;

    private static GameMapManager gmm;

    private GameMapManager(){}

    public static GameMapManager getInstance(){

        if(gmm == null){
            gmm = new GameMapManager();
        }

        return gmm;
    }

    public static int getTilesSize(){
        return TILES_SIZE;
    }
}
