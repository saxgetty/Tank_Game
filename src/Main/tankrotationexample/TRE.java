/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.tankrotationexample;
import Main.GameConstants;
import Main.GameModel.Collidable;
import Main.GameModel.GameObject;
import Main.GameModel.NonStationary.Tank.*;
import Main.GameModel.Resource;
import Main.GameModel.Stationary.Floor;
import Main.GameModel.UserInfo.HealthBar;
import Main.GameModel.UserInfo.TotalLives;
import Main.Launcher;
import Main.Loader.MapLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * @author anthony-pc
 */

public class TRE extends JPanel implements Runnable {

    private BufferedImage world;
    private Graphics2D buffer;
    private Launcher lf;
    public static long tick = 0;
    ArrayList<GameObject> gameObjects;
    ArrayList<Floor> floors;

    public TRE(Launcher lf) {

        this.lf = lf;
    }

    @Override
    public void run() {

        try {

            this.resetGame();
            while (true) {

                this.gameObjects.forEach(GameObject::update);
                this.repaint(); // redraw game

                Collidable.collide(gameObjects);

                tick++;
                Thread.sleep(1000 / 144); //sleep for a few milliseconds

                /*
                 * simulate an end game event
                 * we will do this with by ending the game when 2000 frames have been drawn
                 */
                for (GameObject gameObject : gameObjects) {

                    if (gameObject instanceof Tank && ((Tank) gameObject).getLife() == 0) {
                        this.lf.setFrame("end");
                        return;
                    }
                }
            }
        } catch (InterruptedException ignored) {

            System.out.println(ignored);
        }
    }

    /**
     * Reset game to its initial state.
     */
    public void resetGame() {

        for (GameObject gameObjects : gameObjects) {

            gameObjects.reset();
        }
    }

    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {

        this.world = new BufferedImage(GameConstants.GAME_WORLD_WIDTH,
                GameConstants.GAME_WORLD_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        this.floors = new ArrayList<>();
        this.gameObjects = new ArrayList<>();

        MapLoader.gameInitialize(gameObjects, floors);

        Tank t1 = new Tank(70, 850, (short) 0, Resource.getResourceImage("tank1"), "tank1");
        Tank t2 = new Tank(GameConstants.GAME_WORLD_WIDTH - 120, GameConstants.GAME_WORLD_HEIGHT - 950, (short) 180, Resource.getResourceImage("tank2"), "tank2");

        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_CONTROL);

        this.gameObjects.add(t1);
        this.gameObjects.add(t2);

        this.lf.getJf().addKeyListener(tc1);
        this.lf.getJf().addKeyListener(tc2);
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        TotalLives totalLives = new TotalLives();
        HealthBar healthBar = new HealthBar();

        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.GAME_WORLD_WIDTH, GameConstants.GAME_WORLD_HEIGHT - 100);
        this.floors.forEach(floor -> floor.drawImage(buffer));
        this.gameObjects.forEach(gameObject -> gameObject.drawImage(buffer));

        ArrayList<Tank> tanks = new ArrayList<>();

        for (GameObject gameObject : gameObjects) {

            if (gameObject instanceof Tank) {

                tanks.add((Tank) gameObject);
                gameObject.drawImage(buffer);
            }
        }

        // Drawing the active lives per player and their healthBar
        totalLives.drawImage(g2, tanks);
        healthBar.drawImage(g2, tanks);

        // Creating the left side of the game frame for player 1
        BufferedImage leftHalf = world.getSubimage(
                (tanks.get(0).getX() - GameConstants.GAME_SCREEN_WIDTH / 4 > 0) ?
                        Math.min(tanks.get(0).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_WORLD_WIDTH - (GameConstants.GAME_SCREEN_WIDTH / 2)) :
                        Math.max(tanks.get(0).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, 0),
                (tanks.get(0).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2 > 0) ?
                        Math.min(tanks.get(0).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2, GameConstants.GAME_WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT) :
                        Math.max(tanks.get(0).getY() - (GameConstants.GAME_SCREEN_HEIGHT / 2), 0),
                GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);

        // Creating the right side of the game frame for player 2
        BufferedImage rightHalf = world.getSubimage(
                (tanks.get(1).getX() - GameConstants.GAME_SCREEN_WIDTH / 4 > 0) ?
                        Math.min(tanks.get(1).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, GameConstants.GAME_WORLD_WIDTH - (GameConstants.GAME_SCREEN_WIDTH / 2)) :
                        Math.max(tanks.get(1).getX() - GameConstants.GAME_SCREEN_WIDTH / 4, 0),
                (tanks.get(1).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2 > 0) ?
                        Math.min(tanks.get(1).getY() - GameConstants.GAME_SCREEN_HEIGHT / 2, GameConstants.GAME_WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT) :
                        Math.max(tanks.get(1).getY() - (GameConstants.GAME_SCREEN_HEIGHT / 2), 0),
                GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);
        g2.drawImage(leftHalf, 0, 0, null);
        g2.drawImage(rightHalf, GameConstants.GAME_SCREEN_WIDTH / 2 + 5, 0, null);

        // Create the minimap and place it in a visible/organized location
        BufferedImage mm = world.getSubimage(0, 0, GameConstants.GAME_WORLD_WIDTH, GameConstants.GAME_WORLD_HEIGHT);
        g2.scale(.10, .10);
        g2.drawImage(mm, 6650, 0, null);
    }
}

