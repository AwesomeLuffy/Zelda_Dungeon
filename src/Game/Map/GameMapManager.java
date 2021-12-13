package Game.Map;

public class GameMapManager {

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
