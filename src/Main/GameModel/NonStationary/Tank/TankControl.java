/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GameModel.NonStationary.Tank;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author anthony-pc
 */

public class TankControl implements KeyListener {

    private final Tank t1;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl(Tank newTank, int up, int down, int left, int right, int shoot) {

        this.t1 = newTank;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent key) {

        int keyPressed = key.getKeyCode();

        if (keyPressed == up) {

            this.t1.toggleUpPressed();
        }

        if (keyPressed == down) {

            this.t1.toggleDownPressed();
        }

        if (keyPressed == left) {

            this.t1.toggleLeftPressed();
        }

        if (keyPressed == right) {

            this.t1.toggleRightPressed();
        }

        if (keyPressed == shoot) {

            this.t1.toggleShootPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {

        int keyReleased = key.getKeyCode();

        if (keyReleased == up) {

            this.t1.unToggleUpPressed();
        }

        if (keyReleased == down) {

            this.t1.unToggleDownPressed();
        }

        if (keyReleased == left) {

            this.t1.unToggleLeftPressed();
        }

        if (keyReleased == right) {

            this.t1.unToggleRightPressed();
        }

        if (keyReleased == shoot) {

            this.t1.unToggleShootPressed();
        }
    }
}
