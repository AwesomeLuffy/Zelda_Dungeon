package Game.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    private TiledMap map1;
    private TiledMap map2;

    private static final int TILES_SIZE = 32;

    public Map() throws SlickException {
        this.map1 = new TiledMap(".ressources/maps/map1.tmx");
        this.map2 = new TiledMap(".ressources/maps/map2.tmx");
    }

    public static int getTiles_SIZE(){
        return TILES_SIZE;
    }
    //map1.render(0, 0);
    //map2.render(0, 0);
    public Map(String name){
    }

    /*
    TODO
    getTileIDFromPos(x, y)

     */
}
