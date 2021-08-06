package Main.GameModel.NonStationary.Tank;
import Main.GameModel.NonStationary.NonStationary;
import Main.GameModel.Resource;
import Main.tankrotationexample.TRE;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Tank extends NonStationary {

    private int life;
    private int health;
    private int damage;
    private final String name;
    private final int defaultDamage = 2;
    private final int defaultX;
    private final int defaultY;
    private final int defaultVx = 0;
    private final int defaultVy = 0;
    private final int defaultSpeed = 1;
    private final int defaultLives = 4;
    private final int defaultHealth = 5;
    private final float defaultAngle;
    private final float ROTATIONSPEED = 3.0f;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private final ArrayList<Bullet> ammo;

    public Tank(int x, int y, float angle, BufferedImage img, String name) {

        this.damage = defaultDamage;
        this.x = x;
        this.defaultX = x;
        this.y = y;
        this.defaultY = y;
        this.vx = defaultVx;
        this.vy = defaultVy;
        this.img = img;
        this.angle = angle;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
        this.ammo = new ArrayList<>();
        this.speed = defaultSpeed;
        this.defaultAngle = angle;
        this.life = defaultLives;
        this.health = defaultHealth;
        this.name = name;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public void setVx(int vx) {

        this.vx = vx;
    }

    public void setVy(int vy) {

        this.vy = vy;
    }

    public void setHealth(int health) {

        this.health = health;
    }

    public int getVx() {

        return vx;
    }

    public int getVy() {

        return vy;
    }

    public int getDamage() {

        return this.damage;
    }

    public int getLife() {

        return this.life;
    }

    public int getHealth() {

        return this.health;
    }

    public String getName() {

        return this.name;
    }

    public void incrementDamage() {

        this.damage++;
    }

    public void incrementSpeed() {

        this.speed++;
    }

    public void damageTaken(int damage) {

        this.health -= damage;
    }

    public boolean isUpPressed() {

        return UpPressed;
    }

    public boolean isDownPressed() {

        return DownPressed;
    }

    public void toggleUpPressed() {

        this.UpPressed = true;
    }

    public void toggleDownPressed() {

        this.DownPressed = true;
    }

    public void toggleRightPressed() {

        this.RightPressed = true;
    }

    public void toggleLeftPressed() {

        this.LeftPressed = true;
    }

    public void toggleShootPressed() {

        this.ShootPressed = true;
    }

    public void unToggleUpPressed() {

        this.UpPressed = false;
    }

    public void unToggleDownPressed() {

        this.DownPressed = false;
    }

    public void unToggleRightPressed() {

        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {

        this.LeftPressed = false;
    }

    public void unToggleShootPressed() {

        this.ShootPressed = false;
    }

    public ArrayList<Bullet> getAmmo() {

        return this.ammo;
    }

    protected int getBulletShellX() {

        return getX() + this.img.getWidth();
    }

    protected int getBulletShellY() {

        return getY() + this.img.getHeight() / 2;
    }

    private void rotate(int direction) {

        this.angle += (direction * this.ROTATIONSPEED);
    }

    public void update() {

        if (this.UpPressed) {

            this.movement(1);
        }

        if (this.DownPressed) {

            this.movement(-1);
        }

        if (this.LeftPressed) {

            this.rotate(-1);
        }

        if (this.RightPressed) {

            this.rotate(1);
        }

        if (this.ShootPressed && TRE.tick % 30 == 0) {

            Bullet b = new Bullet(getBulletShellX(), getBulletShellY(), angle, Resource.getResourceImage("bullet"));
            this.ammo.add(b);
        }

        this.ammo.forEach(Bullet::update);

        if (this.life >= 0 && this.health <= 0) {

            respawnTanks();
        }
    }

    @Override
    public void reset() {

        softReset();
        this.life = defaultLives;
    }

    private void respawnTanks() {

        softReset();
        this.life--;
    }

    private void softReset() {

        this.setX(this.defaultX);
        this.setY(this.defaultY);
        this.setVx(this.defaultVx);
        this.setVy(this.defaultVy);
        this.damage = defaultDamage;
        this.angle = this.defaultAngle;
        this.speed = this.defaultSpeed;
        this.health = defaultHealth;
        this.ammo.clear();
        this.hitBox.setLocation(this.defaultX, this.defaultY);
    }

    @Override
    public String toString() {

        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void drawImage(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

        try {

            this.ammo.forEach(bullet -> bullet.drawImage(g));
        } catch (ConcurrentModificationException e) {

            System.out.println(e);
        }
    }
}