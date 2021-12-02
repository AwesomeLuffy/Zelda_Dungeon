package Game.Map;

import java.util.ArrayList;
import java.util.List;

public class GameMapManager {

    private List<Map> allMap = new ArrayList<Map>();

    private static GameMapManager gmm;

    private GameMapManager(){}

    public static GameMapManager getInstance(){

        if(gmm == null){
            gmm = new GameMapManager();
        }

        return gmm;
    }
}
