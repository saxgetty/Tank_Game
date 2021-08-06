package Main.GameModel.Stationary.Wall;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {

    public UnbreakableWall(int x, int y, BufferedImage wallImage) {

        super(x, y, wallImage);
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void reset() {
    }

    @Override
    public void drawImage(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.img, x, y, null);
    }

    @Override
    public void update() {
    }
}