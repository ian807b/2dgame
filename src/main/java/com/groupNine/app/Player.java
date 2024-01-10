package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Sets up the player for the user to control and use
 * to win the game
 * @author Milo
 * @version 1.0
 * @see com.groupNine.app.Moving
 */
public class Player extends Moving {

    /**
     * Used to access other classes
     */
    private gameWindow gameWin;
    /**
     * Used to get the input of the user
     */
    private handleInput inpHandler;
    /**
     * The current score of the player
     */
    private int score;
    /**
     * The time that the game started
     */
    private long startTime;

    /**
     * Initializes variables for the class
     * @param gameWin The gameWindow of the game
     * @param inpHandler The handler for the input
     */
    public Player(gameWindow gameWin, handleInput inpHandler) {
        this.gameWin = gameWin;
        this.inpHandler = inpHandler;
        this.score = 0;
        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Changes the present score of the player
     * @param change The amount of score to increase or decrease
     */
    public void changeScore(int change) {
        this.score += change;
    }

    /**
     * Sets the spawning location and beginning score of the player.
     * Also records the initial time that the player class was called
     */
    public void setDefaultValues() {
        this.xGrid = 2;
        this.yGrid = 2;
        this.score = 50;
        this.direction = "right";
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Sets all of the images that the player will use to
     * portray movement
     */
    public void getPlayerImage() {
        try {
            this.upOne = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-up.png")));
            this.downOne = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-down.png")));
            this.leftOne = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-left.png")));
            this.rightOne = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-right.png")));

            this.upTwo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-up1.png")));
            this.downTwo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-down1.png")));
            this.leftTwo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-left1.png")));
            this.rightTwo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/fish-right1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks for any collision with rewards, special rewards, and punishments
     * @param dir Direction player will be moving
     */
    public void collisionHandler(String dir){
        // Initialize the variables for checking future collisions
        int xDir = 0;
        int yDir = 0;
        // Set the direction of the future movement
        switch (dir) {
            case "up":
                yDir = -1;
                break;
            case "down":
                yDir = 1;
                break;
            case "left":
                xDir = -1;
                break;
            default:  // Right
                xDir = 1;
                break;
        }
        // Check for any collision with rewards
        if (gameWin.map.getTheMap()[this.xGrid+xDir][this.yGrid+yDir] == 2){
            this.changeScore(gameWin.rewardmanage.claimReward(this.xGrid+xDir, this.yGrid+yDir));
        }
        // Check if player is on top of special reward
        else if (gameWin.map.getTheMap()[this.xGrid+xDir][this.yGrid+yDir] == 3){
            this.changeScore(gameWin.specReward.amount);
            // Remove the special reward
            gameWin.specReward.removeReward();
        }
        // Check if player will be on top of punishment
        else if (gameWin.map.getTheMap()[this.xGrid+xDir][this.yGrid+yDir] == 4){
            this.changeScore(gameWin.punishmentList.punishmentArrayList.get(0).getDamage());
            // Remove the punishment
            for (int i = 0; i<gameWin.punishmentList.punishmentArrayList.size(); i++) {
                if (gameWin.punishmentList.punishmentArrayList.get(i).xGrid == this.xGrid+xDir && gameWin.punishmentList.punishmentArrayList.get(i).yGrid == this.yGrid+yDir) {
                    gameWin.punishmentList.punishmentArrayList.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Checks for input from inputHandler, moves the player in that direction,
     * and change the image of the player if needed
     */
    public void update() {
        // Set the direction of movement, call function to check for collisions, move the player
        if (inpHandler.isUp() || inpHandler.isRight() || inpHandler.isLeft() || inpHandler.isDown()) {
            if (inpHandler.isUp() && gameWin.map.getTheMap()[this.xGrid][this.yGrid-1] != -1) {
                direction = "up";
                collisionHandler(direction);
                this.yGrid -= 1;
            } else if (inpHandler.isDown() && gameWin.map.getTheMap()[this.xGrid][this.yGrid+1] != -1) {
                direction = "down";
                collisionHandler(direction);
                this.yGrid += 1;
            } else if (inpHandler.isLeft() && gameWin.map.getTheMap()[this.xGrid-1][this.yGrid] != -1) {
                direction = "left";
                collisionHandler(direction);
                this.xGrid -= 1;
            } else if (inpHandler.isRight() && gameWin.map.getTheMap()[this.xGrid+1][this.yGrid] != -1) {
                direction = "right";
                collisionHandler(direction);
                this.xGrid += 1;
            }
            // Change the position of the player on the grid
            gameWin.map.setPlayerPosition(xGrid,yGrid);
            // Change the sprite to make it visually move
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    /**
     * Gets the present score of the player
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Draws the player onto the screen
     * @param graphics Used to draw onto the screen
     */
    public void draw(Graphics2D graphics) {
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = upTwo;
                }
                else if (spriteNum == 2){
                    image = upOne;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = downTwo;
                }
                else if (spriteNum == 2){
                    image = downOne;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = leftTwo;
                }
                else if (spriteNum == 2){
                    image = leftOne;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = rightTwo;
                }
                else if (spriteNum == 2){
                    image = rightOne;
                }
                if (firstTime){
                    image = rightOne;
                    firstTime = false;
                }
                break;
        }

        graphics.drawImage(image, this.xGrid*gameWin.tileSize, this.yGrid*gameWin.tileSize, gameWin.tileSize, gameWin.tileSize, null);
        String scoreString = "Score: " + this.score;
        graphics.setFont(new Font("Serif", Font.BOLD, 30));
        graphics.drawString(scoreString, 40, gameWin.screenHeight- 40);
        String timePast = "Time: " + ((int) Math.floor(System.currentTimeMillis()-startTime) / 1000);
        graphics.drawString(timePast, gameWin.screenWidth-130, gameWin.screenHeight- 40);
    }

    /**
     * Checks if the player is touching the ending
     * @return True if player touches the ending, False otherwise
     */
    public boolean touchingEndCell() {
        if (gameWin.endCell.isShowEnding()) {
            return gameWin.endCell.getxGrid() == this.xGrid && gameWin.endCell.getyGrid() == this.yGrid;
        }
        return false;
    }

    /**
     * Calculates the total amount of time spent using the startTime
     * @return Total amount of time spent
     */
    public int getTime() {
        return ((int) Math.floor(System.currentTimeMillis()-startTime) / 1000);
    }

    /**
     * Modifier to change the initial start time of the player
     * @param newTime New value to set the time to
     */
    public void changeTime(int newTime) {
        this.startTime = newTime;
    }
}
