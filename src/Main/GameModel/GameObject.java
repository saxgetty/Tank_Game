package Main.GameModel;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected int x, y;
    protected Rectangle hitBox;
    protected BufferedImage img;

    public abstract void reset();

    public abstract void update();

    public abstract void drawImage(Graphics g);

    protected void setX(int x) {

        this.x = x;
    }

    protected void setY(int y) {

        this.y = y;
    }

    public int getX() {

        return this.x;
    }

    public int getY() {

        return this.y;
    }

    protected void resetHitBox() {

        this.hitBox.setRect(0, 0, 0, 0);
    }

    public Rectangle getHitBox() {

        return this.hitBox.getBounds();
    }
}
