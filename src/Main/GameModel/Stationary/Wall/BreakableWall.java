package Main.GameModel.Stationary.Wall;
import Main.GameModel.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * @author Samantha Saxton-Getty (Me)
 * @author Zach Merrill (Brother)
 */

public class BreakableWall extends Wall {

    // 2 - Untouched, 1 - First Hit, 0 - Broken
    public int wallStatus = 2;

    public BreakableWall(int x, int y, BufferedImage wallImage) {

        super(x, y, wallImage);
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }

    public void changeState() {

        wallStatus--;
    }

    @Override
    public void reset() {

        wallStatus = 2;
        this.hitBox.setRect(x, y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void drawImage(Graphics g) {

        if (wallStatus == 2) {

            g.drawImage(this.img, x, y, null);
        } else if (wallStatus == 1) {

            g.drawImage(Resource.getResourceImage("break1"), x, y, null);
        } else {

            g.drawImage(Resource.getResourceImage("floor1"), x, y, null);
            resetHitBox();
        }
    }

    @Override
    public void update() {

    }
}