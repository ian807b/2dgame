package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Displays an ending screen which changes based on
 * whether the user wins or loses
 * @author Ian
 * @version 1.0
 */
public class endScreen {
    private gameWindow gameWin;
    /**
     * Holds the state of the game
     */
    private boolean winLoseState;
    /**
     * Ensures we only use the setWinLoseState function once
     */
    private boolean firstTime;
    /**
     * Stores the time that the player took to finish
     * the game
     */
    private double endTime;
    /**
     * Holds the image for the background of the ending screen
     */
    private BufferedImage image;

    /**
     * Saves the gameWindow and also sets the background image of the end screen
     * @param gameWin The gameWindow of the game
     */
    public endScreen(gameWindow gameWin) {
        this.gameWin = gameWin;
        this.firstTime = true;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/background/backdrop.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the result of the game and the time the player
     * spent playing
     * @param result The result of either a win or lose
     */
    public void setWinLoseState(boolean result) {
        if (firstTime == true){
            winLoseState = result;
            if (gameWin != null){
                if (gameWin.player != null){
                    this.endTime = gameWin.player.getTime();
                }
            }
            firstTime = false;
        }
    }

    /**
     * Illustrates either the victory or loss screen depending on
     * the result of the game
     * @param graphic Used to draw on the screen
     */
    public void draw(Graphics2D graphic) {
        // Resulting Screen for Winning
        if (winLoseState == true) {
            graphic.drawImage( image, 0, 0,Color.white , null);
            graphic.setColor(Color.lightGray);
            graphic.setFont(new Font("Serif", Font.PLAIN, 90));
            graphic.drawString("Victory", (gameWin.screenWidth / 2) - 130, (gameWin.screenHeight / 2) - 100);
            graphic.setColor(Color.lightGray);
            graphic.setFont(new Font("Serif", Font.PLAIN, 30));

            String credits = "Score: " + gameWin.player.getScore() + " | Time: " + endTime;

            graphic.drawString(credits, (gameWin.screenWidth / 2) - 150, (gameWin.screenHeight / 2) + 200);
        }
        // Resulting Screen for Losing
        else {
            graphic.drawImage( image, 0, 0,Color.white , null);
            graphic.setColor(Color.lightGray);
            graphic.setFont(new Font("Serif", Font.PLAIN, 90));
            graphic.drawString("Defeat", (gameWin.screenWidth / 2) - 130, (gameWin.screenHeight / 2) - 100);
        }
    }

    /**
     * Getter for ending state of game
     * @return Boolean value of game state
     */
    public boolean isWinLoseState() {
        return winLoseState;
    }

    /**
     * Getter for if first time
     * @return Boolean value of first time
     */
    public boolean isFirstTime() {
        return firstTime;
    }

    /**
     * Getter for time the game ended
     * @return double value for time of game's ending
     */
    public double getEndTime() {
        return endTime;
    }

    /**
     * Getter for the image of the screen
     * @return BufferedImage of the image
     */
    public BufferedImage getImage() {
        return image;
    }
}
