package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Extends the abstract reward to create a regular reward for the player to interact with
 * @author Parmida
 * @version 1.0
 * @see com.groupNine.app.reward
 */
public class regularReward extends reward{

    /**
     * Constructor for a regular reward object.
     * @param gameWin gameWindow object to give the class access to its variables.
     * @param tmpX X-coordinate
     * @param tmpY Y-coordinate
     */
    public regularReward(gameWindow gameWin, int tmpX, int tmpY) {
        this.gameWin = gameWin;
        this.amount = 50;
        this.xGrid = tmpX;
        this.yGrid = tmpY;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/reward/regular.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method is to draw an image onto the gameWindow object.
     * @param graphic Graphics2D object to draw an image at a specific position.
     */
    public void draw(Graphics2D graphic) {
        graphic.drawImage(image, this.xGrid*gameWin.tileSize, this.yGrid*gameWin.tileSize, gameWin.tileSize, gameWin.tileSize, null);
    }

}
