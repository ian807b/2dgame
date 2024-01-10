package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Sets up the ending portal for the player after
 * the collection all the regular reward
 * @author Alice
 * @version 1.0
 * @see com.groupNine.app.Entity
 */
public class endingCell extends Entity{
    private gameWindow gameWin;
    /**
     * Holds the image of the ending cell
     */
    private BufferedImage image;
    /**
     * Determines if the ending cell should show itself on the screen
     */
    private boolean showEnding;

    /**
     * initialize the showEnding to be false, and store the endingPortal image to this image *** need update ***
     * @param gameWin input the game Window of the game
     */
    public endingCell(gameWindow gameWin) {
        this.gameWin = gameWin;
        this.showEnding = false;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/endingPortal/portal.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to set up the position of the ending portal after all regular rewards are collected by the player,
     * and assign showEnding to be true
     */
    public void setUpEndingCell() {
        this.xGrid = 14;
        this.yGrid = 9;
        this.showEnding = true;
    }

    /**
     * Gets the private variable image
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * This method checks if the ending portal is being show or not
     * @return the values true or false for the showEnding boolean variable
     */
    public boolean isShowEnding() {
        return showEnding;
    }

    /**
     * This method draw the ending portal on the game window
     * @param graphic the graphical display on the game window
     */
    public void draw(Graphics2D graphic) {
        graphic.drawImage(image, this.xGrid*gameWin.tileSize, this.yGrid*gameWin.tileSize, gameWin.tileSize, gameWin.tileSize, null);
    }

}
