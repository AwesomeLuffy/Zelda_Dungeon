package Game.Character.Colision;

import Game.Animations;
import Game.Map.GameMapManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.List;

public class GameCollision {

    private Rectangle rectangle;

    public GameCollision(Vector2f pos){
        this.rectangle = new Rectangle(pos.getX() * GameMapManager.getTilesSize(),
                pos.getY() * GameMapManager.getTilesSize(),
                GameMapManager.getTilesSize(),
                GameMapManager.getTilesSize());
    }

    public void setGameGolisionPos(Vector2f vector2f){
        this.rectangle.setLocation(
                new Vector2f(vector2f.getX() * GameMapManager.getTilesSize(),
                        vector2f.getY() * GameMapManager.getTilesSize()));
    }

    public Vector2f getGameColisionPos(Vector2f vector2f){
        return this.rectangle.getLocation();
    }

    public Rectangle getRectangle(){ return this.rectangle; }

    public boolean isColideTo(Rectangle rectangle){
        return this.rectangle.intersects(rectangle);
    }

    public void drawRect(Graphics graphics){
        graphics.draw(this.rectangle);
    }

//    public List<Animations> isBlock(Rectangle rectangle){
//        if(this.rectangle.getLocation() == rectangle.getLocation()){
//            return true;
//        }
//        else if()
//    }
}
