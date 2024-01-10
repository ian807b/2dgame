package com.groupNine.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * HandleInput class implements the KeyListener interface for receiving keyboard events
 * @author Milo
 * @version 1.0
 * @see java.awt.event.KeyListener
 */
public class handleInput implements KeyListener {
    /**
     * Check if the up, down, left and right key is pressed
     */
    private boolean up, down, left, right;

    /**
     * Gets any key typed
     * @param event the event to be processed
     */
    public void keyTyped(KeyEvent event) {
        return;
    }

    /**
     * This method checks which key is pressed and assign true to the corresponding boolean type of that key
     * @param event An event which indicates that a keystroke occurred
     */
    public void keyPressed(KeyEvent event) {
        int code = event.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = true;
        }

        if (code == KeyEvent.VK_DOWN) {
            down = true;
        }

        if (code == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (code == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    /**
     * This method checks if the input key is released and assign false to the corresponding boolean type of that key
     * @param event An event which indicates that a keystroke occurred
     */
    public void keyReleased(KeyEvent event) {

        int code = event.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = false;
        }

        if (code == KeyEvent.VK_DOWN) {
            down = false;
        }

        if (code == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (code == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    /**
     * This method checks if the up key is being pressed or not
     * @return the values true or false for the up key boolean variable
     */
    public boolean isUp() {
        return this.up;
    }

    /**
     * This method checks if the down key is being pressed or not
     * @return the values true or false for the down key boolean variable
     */
    public boolean isDown() {
        return this.down;
    }

    /**
     * This method checks if the left key is being pressed or not
     * @return the values true or false for the left key boolean variable
     */
    public boolean isLeft() {
        return this.left;
    }

    /**
     * This method checks if the right key is being pressed or not
     * @return the values true or false for the right key boolean variable
     */
    public boolean isRight() {
        return this.right;
    }

}
