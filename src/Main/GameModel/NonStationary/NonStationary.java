package Main.GameModel.NonStationary;
import Main.GameModel.GameObject;

public abstract class NonStationary extends GameObject {

    protected int vx, vy, speed;
    protected float angle;

    protected void movement(int direction) {

        vx = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
        x += (direction) * vx;
        y += (direction) * vy;
        moveBound();
    }

    protected void moveBound() {

        this.hitBox.setLocation(x, y);
    }
}
