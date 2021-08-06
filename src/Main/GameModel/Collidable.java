package Main.GameModel;
import Main.GameModel.NonStationary.Tank.Tank;
import Main.GameModel.Stationary.PowerUp.PowerUp;
import Main.GameModel.Stationary.Wall.BreakableWall;
import java.util.ArrayList;

/**
 * @author Melissa Ho (Classmate)
 * @author Samantha Saxton-Getty (Me)
 * @author Zach Merrill (Brother)
 */

public class Collidable {

    public static void collide(ArrayList<GameObject> gameObject) {

        for (GameObject tank : gameObject) {

            if (tank instanceof Tank) {

                for (GameObject leftover : gameObject) {

                    direction(tank, leftover);
                    bulletCollision(tank, leftover);
                    powerUpCollision(tank, leftover);
                }
            }
        }
    }

    private static void bulletCollision(GameObject tank, GameObject leftover) {

        for (int i = 0; i < ((Tank) tank).getAmmo().size(); i++) {

            if ((((Tank) tank).getAmmo().get(i).getHitBox().intersects(leftover.getHitBox())) && tank != leftover) {

                if (leftover instanceof BreakableWall) {

                    ((BreakableWall) leftover).changeState();
                }

                if (leftover instanceof Tank) {

                    ((Tank) leftover).damageTaken(((Tank) tank).getDamage());
                }
                ((Tank) tank).getAmmo().remove(i--);
            }
        }
    }

    private static void powerUpCollision(GameObject tank, GameObject powerUp) {

        if (powerUp instanceof PowerUp && tank.getHitBox().intersects(powerUp.getHitBox())) {

            ((PowerUp) powerUp).applyPowerUp((Tank) tank);
        }
    }

    private static void direction(GameObject tank, GameObject leftover) {

        if (tank.getHitBox().intersects(leftover.getHitBox()) && tank != leftover && !(leftover instanceof PowerUp)) {

            if (((Tank) tank).isUpPressed()) {

                tank.setX(tank.getX() - ((Tank) tank).getVx());
                tank.setY(tank.getY() - ((Tank) tank).getVy());
            } else if (((Tank) tank).isDownPressed()) {

                tank.setX(tank.getX() + ((Tank) tank).getVx());
                tank.setY(tank.getY() + ((Tank) tank).getVy());
            }
        }
    }
}
