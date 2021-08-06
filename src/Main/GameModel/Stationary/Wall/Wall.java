package Main.GameModel.Stationary.Wall;
import Main.GameModel.Stationary.Stationary;
import java.awt.image.BufferedImage;

public abstract class Wall extends Stationary {

    public Wall(int x, int y, BufferedImage img) {

        this.x = x;
        this.y = y;
        this.img = img;
    }
}