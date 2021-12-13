package Game.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {
    private TiledMap map;
    private String name;
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

    public boolean canMoveToRight(Vector2f heroPosition, boolean ifKey){
        return this.canMoveTo((int)heroPosition.getX() + 1, (int)heroPosition.getY(), ifKey);
    }

    public boolean canMoveToLeft(Vector2f heroPosition, boolean ifKey){
        return this.canMoveTo((int)heroPosition.getX() - 1, (int)heroPosition.getY(), ifKey);
    }

    public boolean canMoveToDown(Vector2f heroPosition, boolean ifKey){
        return this.canMoveTo((int)heroPosition.getX(), (int)heroPosition.getY() + 1, ifKey);
    }

    public boolean canMoveToUp(Vector2f heroPosition, boolean ifKey){
        return this.canMoveTo((int)heroPosition.getX(), (int)heroPosition.getY() - 1, ifKey);
    }

    public boolean canMoveTo(int x, int y, boolean ifKey){
        boolean move = false;
        int indexClaqueBorder;
        int tileId;
        indexClaqueBorder = this.map.getLayerIndex("Border");
        tileId = this.map.getTileId(x,y,indexClaqueBorder);
        if (tileId == 0){
            if (ifKey == false && (x == 11 || x == 12 || x == 13) && y == 0){}
            else { move = true; }
        }
        return move;
    }

    public boolean changeMap(Vector2f heroPosition){
        int x = (int)heroPosition.getX();
        int y = (int)heroPosition.getY();
        boolean teleporting;
        int indexClaqueDoor;
        int tileId;
        indexClaqueDoor = this.map.getLayerIndex("Door");
        tileId = this.map.getTileId(x,y,indexClaqueDoor);
        if (tileId == 0){
            teleporting = false;
        }
        else {
            teleporting = true;
        }
        return teleporting;
    }
}
