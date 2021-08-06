package Main.GameModel.Stationary;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * @author Melissa Ho (Classmate)
 * @author Samantha Saxton-Getty (Me)
 */

public class Floor {

    int x, y;
    BufferedImage floorImage;

    public Floor(int x, int y, BufferedImage floorImage) {

        this.x = x;
        this.y = y;
        this.floorImage = floorImage;
    }

    public void drawImage(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.floorImage, x, y, null);
    }

//    public void update() {
//
//    }
}