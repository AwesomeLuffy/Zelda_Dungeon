package Game.Animation;

import Game.Map.GameMapManager;
import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Graphics;

import java.awt.*;

public class GameImage {

    private final String name;
    private Image image;
    private Vector2f position;
    private Vector2f spritePosition;
    private int tileSize;

    public GameImage(String _name, Image _image){
        this.name = _name;
        this.image = _image;
        this.position = new Vector2f(1, 1);
    }

    public GameImage(String _name, Image _image, Vector2f position, Vector2f spritePosition, int tileSize){
        this(_name, _image);
        this.position = position;
        this.spritePosition = spritePosition;
        this.tileSize = tileSize;

        this.setImageFromSprite(spritePosition, tileSize);
    }

    public GameImage(String _name, Image _image, Vector2f spritePosition, int tileSize){
        this(_name, _image);
        this.spritePosition = spritePosition;
        this.position = new Vector2f(1, 1);
        this.tileSize = tileSize;

        this.setImageFromSprite(this.spritePosition, this.tileSize);
    }

    private void setImageFromSprite(Vector2f spritePosition, int tilesSize){
            this.image = this.image.getSubImage((int) spritePosition.getX() * tilesSize,
                    (int) spritePosition.getY() * tilesSize,
                    tilesSize, tilesSize);
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

    public void drawImage(Vector2f vector2f, Graphics graphics){
        graphics.drawImage(
                this.image,
                vector2f.getX() * GameMapManager.getTilesSize(),
                vector2f.getY() * GameMapManager.getTilesSize());
    }

    public void drawImage(Graphics graphics){
        this.drawImage(this.position, graphics);
    }
}
