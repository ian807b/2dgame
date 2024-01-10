package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Stores the position and the image for the punishments in the game
 * @author Ian
 * @version 1.0
 * @see com.groupNine.app.Entity
 */
public class Punishment extends Entity{

    private int damage;
    private gameWindow gameWin;
    /**
     * Used to store the image of the punishment
     */
    BufferedImage image;

    /**
     * Assign the x and y coordinates to the punishment, and the parameter gameWin to this gameWin.
     * Setting the player score damage to be -30.
     * Storing the punishment image to this image   *** need update ***
     *
     * @param gameWin input the game Window of the game
     * @param x x coordinate of the punishment
     * @param y y coordinate of the punishment
     */
    public Punishment(gameWindow gameWin, int x, int y){
        this.xGrid = x;
        this.yGrid = y;
        this.gameWin = gameWin;
        this.damage = -30;

        image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/enemy/stationary.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the amount of damage dealt to another Entity.
     * @return this damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets the private variable image
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * This method draw the fishing hook on the game window
     * @param graphic The graphical display on the game window
     */
    public void draw(Graphics2D graphic) {
        graphic.drawImage(image, this.xGrid*gameWin.tileSize, this.yGrid*gameWin.tileSize, gameWin.tileSize, gameWin.tileSize, null);
    }
}
