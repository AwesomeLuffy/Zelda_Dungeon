package Game.Character.Colision;

import Game.Character.Type.Enemy;
import Game.Utils.Pair;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameCollisionManager {

    private static GameCollisionManager gameColisionManager;

    private HashMap<Object, GameCollision> allColision = new HashMap<>();
    private List<Collisions> collisions = new ArrayList<>();

    private GameCollisionManager(){}

    public static GameCollisionManager getInstance(){
        if(gameColisionManager == null){
            gameColisionManager =  new GameCollisionManager();
        }
        return gameColisionManager;
    }

    public GameCollision getGameColision(Object obj){
        if(this.allColision.containsKey(obj)){
            return this.allColision.get(obj);
        }
        return null;
    }

    public void initCollides(){
        for(Collisions collision : this.collisions){
            collision.updateColide();
        }
    }

    public HashMap<Object, GameCollision> getAllColision() {
        return allColision;
    }

    public void addCollideObject(Object obj, Vector2f pos){
        if(!this.allColision.containsKey(obj)){
            this.allColision.put(obj, new GameCollision(pos));
        }
    }

    public Pair<Boolean, Object> isObjectCollide(Object obj){
        for(Map.Entry<Object, GameCollision> entry : allColision.entrySet()){
            if(this.allColision.get(obj).isColideTo(entry.getValue().getRectangle())){
                return new Pair<>(true, entry.getKey());
            }
        }
        return new Pair<>(false, null);
    }

    public void updateColidePos(Object obj, Vector2f pos){
        this.allColision.get(obj).setGameGolisionPos(pos);
    }

    public boolean isInColideCollection(Object obj){
        return this.allColision.containsKey(obj);
    }

    public void registerCol(Collisions collision){
        this.collisions.add(collision);
    }

    public GameCollision getColideWith(Object obj){
        if(this.isObjectCollide(obj).getKey()){
            return this.allColision.get(this.isObjectCollide(obj).getValue());
        }
        return null;
    }
}
