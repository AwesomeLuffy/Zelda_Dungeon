package Game.Map;

public class GameMapManager {

    private static GameMapManager gmm;

    private GameMapManager(){}

    public static GameMapManager getInstance(){

        if(gmm == null){
            gmm = new GameMapManager();
        }

        return gmm;
    }
}
