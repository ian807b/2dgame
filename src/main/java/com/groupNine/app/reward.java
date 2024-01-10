package com.groupNine.app;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base template for rewards which contain how much points players will recieve on collision
 * @author Parmida
 * @version 1.0
 * @see com.groupNine.app.Entity
 */
public abstract class reward extends Entity{
    /**
     * amount represents the amount of the points added to the player
     * image is to store image file
     *
     */
    int amount;
    gameWindow gameWin;
    BufferedImage image;

    /**
     * Instantiates default values for the reward
     */
    public void setup() {}

    /**
     * Graphics2D object to place (or draw) it onto the map.
     * @param graphic Used to illustrate reward onto the screen
     */
    public void draw(Graphics2D graphic) {
    }


}
