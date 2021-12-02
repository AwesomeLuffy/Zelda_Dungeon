package Game.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
    private TiledMap map;

    public Map() throws SlickException {
        this.map = new TiledMap(".ressources/maps/map1.tmx");
    }

    public Map(String name){
        map.render(0, 0);
    }

    /*
    TODO
    getTileIDFromPos(x, y)

     */
}
