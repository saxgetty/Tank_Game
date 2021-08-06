package Main.GameModel.Stationary.PowerUp;
import Main.GameModel.NonStationary.Tank.Tank;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthUp extends PowerUp {

    public HealthUp(int x, int y, BufferedImage img) {

        super(x, y, img);
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void applyPowerUp(Tank tank) {

        tank.setHealth(tank.getHealth() + 1);
        usedStatus = true;
        this.resetHitBox();
    }

    @Override
    public void reset() {
        usedStatus = false;
        this.hitBox.setRect(x, y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void update() {

    }
}
