package com.groupNine.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Instantiates and keeps the game functional
 * @author Ian
 * @version 1.1
 * @see javax.swing.JPanel
 * @see java.lang.Runnable
 */
public class gameWindow extends JPanel implements Runnable {

    // Settings for the screen
    /**
     * 16 pixels x pixels for all objects in the game.
     */
    int orgTileSize = 16; // 16 pixels by 16 pixels
    /**
     * Scaling factor for high resolutions. Objects will now become 64 pixels x 64 pixels
     */
    int scale = 4;
    /**
     * Each tile contains 64 pixels x 64 pixels
     */
    int tileSize = orgTileSize * scale; // Amount of tiles able to be displayed
    /**
     * Game screen will contain 16 tiles horizontally.
     */
    int maxScreenCol = 16; // x
    /**
     * Game screen will contain 12 tiles vertically.
     */
    int maxScreenRow = 12; // y
    /**
     * 64 pixels * 16 columns = 1024 pixels
     */
    int screenWidth = tileSize * maxScreenCol;
    /**
     * 64 pixels * 12 rows = 768 pixels
     */
    int screenHeight = tileSize * maxScreenRow;

    /**
     * Boolean value to track the status of the game (Playing, or ended)
     */
    public volatile boolean gameEnd = false;
    /**
     * Boolean value to track the result of the game. (win, or lose)
     */
    public volatile boolean gameWinLose = false;
    /**
     * Handles which key has been pressed for movement
     */
    handleInput inpHandler;
    /**
     * Thread constructor for run method.
     */
    public Thread gameThread;

    /**
     * FPS; the number of the screen is updated.
     */
    int FPS = 5;

    /**
     * The frame that the gameWindow is placed in
     */
    JFrame window;
    /**
     * Variable to hold itself
     */
    gameWindow gameWin;

    /**
     * Action to happen when the special reward is spawned
     */
    ActionListener spawnSpecialReward;
    /**
     * Action to happen to remove the special reward from the screen
     */
    ActionListener removeSpecialReward;
    /**
     * Actino to happen when the game ends
     */
    ActionListener endTheGame;
    /**
     * Timer to calculate when to spawn in the special reward
     */
    Timer timer;
    /**
     * Timer to calculate when the special reward should be removed
     */
    Timer removeTimer;
    /**
     * Timer to see when the game over screen should be removed
     */
    Timer endScreenTimer;

    /**
     * Variable to hold all the walls
     */
    public wallManager walls;
    /**
     * Variable to hold the different position of object on the screen
     */
    public gameMap map;
    /**
     * Variable to hold all of the rewards
     */
    public rewardManager rewardmanage;
    /**
     * Variable to spawn in the special reward
     */
    public specialReward specReward;
    /**
     * Variable to hold the punishment list
     */
    public PunishmentList punishmentList;
    /**
     * Variable to hold the ending cell
     */
    public endingCell endCell;
    /**
     * Variable to hold the end screen
     */
    public endScreen screenEnd;
    /**
     * Variable to hold the player
     */
    public Player player;
    /**
     * Variable to hold the enemy
     */
    public Enemy enemy;

    /**
     * Constructor of the gameWindow class; sets up the settings of the game screen.
     * @param window A JFrame object where we place JPanel objects.
     */
    public gameWindow(JFrame window) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setSize((new Dimension(screenWidth, screenHeight)));
        this.setBackground(Color.black); // Background color
        this.setDoubleBuffered(true); // Increases rendering performance
        this.setFocusable(true);
        this.window = window;
        gameWin = this;
    }

    /**
     * Starts the thread.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The end screen is displayed, regardless of the result of the game
     */
    public void playerWinLose() {
        gameEnd = true;
        endScreenTimer = new Timer(10000, endTheGame);
        endScreenTimer.start();
        repaint();
    }

    /**
     * Starts the game
     */
    public void startGameStage() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        // Game loop
        while (gameThread != null) {
            // Ensure the game does not run too fast
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;

            if (delta >= 1) {
                delta--;
                // Check for player score above 0
                if (player.getScore() < 0) {
                    // Lose
                    playerWinLose();
                }
                // Check if enemy touches player
                else if (enemy.isTouchedPlayer()) {
                    // Lose
                    playerWinLose();
                }
                else {

                    // Check if all rewards are collected
                    if (rewardmanage.rewardList.size() == 0) {
                        // Spawn ending cell
                        endCell.setUpEndingCell();
                        if (player.touchingEndCell()){ // Player is now touching the end cell
                            // End game
                            gameWinLose = true;
                            playerWinLose();
                        }
                        else {
                            // Update information
                            update();
                            // Draw screen with updated information
                            repaint();
                        }
                    }
                    else {
                        // Update information
                        update();
                        // Draw screen with updated information
                        repaint();
                    }
                }


                gameWin.requestFocus();
            }
        }
    }

    /**
     * Initalizes all of the variables needed for the game loop to function.
     * Creates timers for special rewards.
     */
    public void run() {
        gameWin.setFocusable(true);
        gameWin.requestFocus();
        inpHandler = new handleInput();
        gameWin.addKeyListener(inpHandler);

        // Initializing the variables for the game
        map = new gameMap(gameWin);
        walls = new wallManager(gameWin);
        rewardmanage = new rewardManager(gameWin);
        specReward = new specialReward(gameWin);
        punishmentList = new PunishmentList(gameWin);
        endCell = new endingCell(gameWin);
        player = new Player(gameWin, inpHandler);
        enemy = new Enemy(gameWin);

        // Initializing the Timer and Listener
        removeSpecialReward = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specReward.removeReward();
                removeTimer.stop();
            }
        };
        spawnSpecialReward = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specReward.initiateSpawn();
                removeTimer = new Timer(4000, removeSpecialReward); // Delete the special reward in 4000ms
                removeTimer.start();
                timer.stop();
            }
        };
        endTheGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                gameWin.setVisible(false);
                gameThread.stop();
                endScreenTimer.stop();
            }
        };

        // Randomly spawn the reward
        Random rand = new Random();
        int randTime = (rand.nextInt(20) + 5) * 1000;
        timer = new Timer(randTime, spawnSpecialReward); // Waits 5000ms to spawn reward
        timer.start();

        screenEnd = new endScreen(gameWin);

        startGameStage();
    }

    /**
     * Constantly ensure the player and enemy are being called every tick
     */
    public void update() {
        if (player != null){
            player.update();
        }
        if (enemy != null){
            enemy.update();
        }
    }

    /**
     * Draws the different entities onto the screen depending on the state of various objects
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic = (Graphics2D) g; // Sprite of main character
        graphic.setBackground(Color.white);
        if (!gameEnd){
            if (walls !=null){
                walls.draw(graphic);
            }
            if (rewardmanage != null){
                rewardmanage.draw(graphic);
            }
            if (specReward != null){
                if (specReward.isCanSpawn()){
                    specReward.draw(graphic);
                }
            }
            if (endCell != null){
                if (endCell.isShowEnding()) {
                    endCell.draw(graphic);
                }
            }
            if (punishmentList != null){
                punishmentList.draw(graphic);
            }
            if (player != null){
                player.draw(graphic);
            }
            if (enemy != null){
                enemy.draw(graphic);
            }
        }
        else {
            if(gameWinLose == true) { // Show win, score and time
                screenEnd.setWinLoseState(true);
                screenEnd.draw(graphic);
            }
            else { // Show lose
                screenEnd.setWinLoseState(false);
                screenEnd.draw(graphic);
            }
        }

        graphic.dispose(); // Helps save memory

    }

}
