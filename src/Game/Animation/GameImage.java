package Game.Animation;

import Game.Map.GameMapManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Graphics;

import java.awt.*;

public class GameImage {

    private final String name;
    private final Image image;
    private Vector2f position;

    public GameImage(String _name, Image _image){
        this.name = _name;
        this.image = _image;
        this.position = new Vector2f(1, 1);
    }

    public GameImage(String _name, Image _image, Vector2f vector2f){
        this(_name, _image);
        this.position = vector2f;
    }

    public void drawImage(Vector2f vector2f, Graphics graphics){
        graphics.drawImage(
                this.image,
                vector2f.getX() * GameMapManager.getTilesSize(),
                vector2f.getY() * GameMapManager.getTilesSize());
    }

    public void drawImage(Graphics graphics){
        this.drawImage(this.position, graphics);
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
}
