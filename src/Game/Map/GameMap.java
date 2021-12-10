package Game.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {
    private TiledMap map;
    private String name;
    //private TiledMap map2;
    private static final int TILES_SIZE = 32;

    public GameMap(String name, String path) throws SlickException {
        this.map = new TiledMap(path);
        this.name = name;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public void render(){
        this.map.render(0, 0);
    }

    public static int getTiles_SIZE(){
        return TILES_SIZE;
    }

    public boolean canMoveToRight(Vector2f heroPosition){
        return this.canMoveTo((int)heroPosition.getX() + 1, (int)heroPosition.getY());
    }

    public boolean canMoveToLeft(Vector2f heroPosition){
        return this.canMoveTo((int)heroPosition.getX() - 1, (int)heroPosition.getY());
    }

    public boolean canMoveToDown(Vector2f heroPosition){
        return this.canMoveTo((int)heroPosition.getX(), (int)heroPosition.getY() + 1);
    }

    public boolean canMoveToUp(Vector2f heroPosition){
        return this.canMoveTo((int)heroPosition.getX(), (int)heroPosition.getY() - 1);
    }


    public boolean canMoveTo(int x, int y){
        boolean move;
        int indexClaqueBorder;
        int tileId;
        indexClaqueBorder = this.map.getLayerIndex("Border");
        tileId = this.map.getTileId(x,y,indexClaqueBorder);
        if (tileId == 0){
            move = true;
        }
        else {
            move = false;
        }
        return move;
    }
}
