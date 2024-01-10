package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Holds everything needed to create and display a special reward
 * @author Parmida
 * @version 1.0
 * @see com.groupNine.app.reward
 */
public class specialReward extends reward{
    private boolean canSpawn;

    /**
     * Sets up an initial condition for the special reward.
     * @param gameWin GameWindow object to gain access to its variables.
     */
    public specialReward(gameWindow gameWin) {
        // Set the general values
        this.gameWin = gameWin;
        this.amount = 100;
        this.canSpawn = false;

        // Get the image
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/reward/special.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Randomize x and y coordinates without any collision
     */
    public void randomizePosition(){
        Random rand = new Random();
        this.xGrid = rand.nextInt(gameWin.maxScreenCol);
        this.yGrid = rand.nextInt(gameWin.maxScreenRow);
        while(gameWin.map.getTheMap()[this.xGrid][this.yGrid] != 0 && this.yGrid <= gameWin.maxScreenRow && gameWin.map.getEnemyPlayerMap()[this.xGrid][this.yGrid] != 0) {
            this.xGrid = rand.nextInt(gameWin.maxScreenCol);
            this.yGrid = rand.nextInt(gameWin.maxScreenRow);
        }
    }

    /**
     * Randomly places the special award.
     */
    public void initiateSpawn() {
        this.canSpawn = true;

        // Randomize the x and y coordinates
        randomizePosition();

        // Place it into the collision handler
        gameWin.map.setSpecialReward(this.xGrid, this.yGrid);

    }

    /**
     * Acts like a getter for the special reward.
     * @return A boolean value for the presence of the special reward.
     */
    public boolean isCanSpawn() {
        return canSpawn;
    }

    /**
     * Draws the special reward at the randomly generated coordinate.
     * @param graphic Graphics2D object to draw the regular awards onto the map.
     */
    public void draw(Graphics2D graphic) {
        graphic.drawImage(image, this.xGrid*gameWin.tileSize, this.yGrid*gameWin.tileSize, gameWin.tileSize, gameWin.tileSize, null);
    }

    /**
     * Removes the special reward from the map.
     */
    public void removeReward() {
        canSpawn = false;
        if (gameWin.map.getTheMap()[this.xGrid][this.yGrid] == 3) {
            gameWin.map.getTheMap()[this.xGrid][this.yGrid] = 0;
        }
    }
}
