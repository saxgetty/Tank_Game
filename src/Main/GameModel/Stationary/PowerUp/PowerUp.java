package Main.GameModel.Stationary.PowerUp;
import Main.GameModel.NonStationary.Tank.Tank;
import Main.GameModel.Resource;
import Main.GameModel.Stationary.Stationary;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUp extends Stationary {

    // Indicates if the power up is used or not via boolean status
    protected boolean usedStatus = false;

    public PowerUp(int x, int y, BufferedImage img) {

        this.x = x;
        this.y = y;
        this.img = img;
    }

    public abstract void applyPowerUp(Tank tank);

    @Override
    public void drawImage(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if (usedStatus) {

            g2.drawImage(Resource.getResourceImage("floor2"), x, y, null);
        } else {

            g2.drawImage(this.img, x, y, null);
        }
    }

    @Override
    public void update() {

    }
}

