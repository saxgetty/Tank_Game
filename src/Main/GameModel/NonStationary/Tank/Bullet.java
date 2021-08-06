package Main.GameModel.NonStationary.Tank;
import Main.GameModel.NonStationary.NonStationary;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends NonStationary {

    public Bullet(int x, int y, float angle, BufferedImage bulletShellImage) {

        this.x = x;
        this.y = y;
        this.speed = 7;
        this.angle = angle;
        this.img = bulletShellImage;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }

    public void drawImage(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        g2d.setColor(Color.BLACK);
    }

    public void update() {

        movement(1);
    }

    @Override
    public void reset() {

    }
}
