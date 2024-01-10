package com.groupNine.app;

import java.util.Arrays;

/**
 * Sets up the map that will be displayed on the screen and used for collision
 * @author Ian
 * @version 1.0
 */
public class gameMap {

    private gameWindow gameWin;
    /**
     * Holds the objects that should be displayed in each grid.
     * With -1 representing a barrier, 0 representing nothing, 1 representing the player,
     * 2 representing a regular reward, 3 representing a special reward, and 4 representing a punishment.
     */
    private int[][] theMap;
    /**
     * Holds the player and enemy position.
     * Player is denoted with 1, enemy is denoted with 2.
     * The array is for checking and updating the player and enemy positions.
     * Every other values, -1, 2, 3, and 4, from "theMap" are not needed here
     * because they are checked by the collisionHandler and the Player classes.
     */
    private int[][] enemyPlayerMap;

    /**
     * Assign the parameter gameWin to this gameWin, and calls the mapInitializer method
     * @param gameWin input the game Window of the game
     */
    public gameMap(gameWindow gameWin) {
        this.gameWin = gameWin;
        mapInitializer();
    }

    /**
     * Creates a game map int 2D array template,
     * and set up the enemyPlayerMap to hold the position of both the player and enemy
     */
    public void mapInitializer() {
        theMap = new int[gameWin.maxScreenCol][gameWin.maxScreenRow];
        int[][] tmps = {
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0},
                {-1,0,0,0,0,0,0,2,0,0,-1,0},
                {-1,0,0,-1,-1,-1,-1,-1,0,0,-1,0},
                {-1,0,0,-1,0,0,4,-1,0,0,-1,0},
                {-1,0,-1,-1,0,0,-1,0,2,0,-1,0},
                {-1,0,-1,0,0,0,0,-1,0,0,-1,0},
                {-1,0,-1,2,0,-1,0,0,-1,0,-1,0},
                {-1,0,-1,0,0,0,0,0,0,0,-1,0},
                {-1,0,-1,0,0,0,-1,-1,0,0,-1,0},
                {-1,0,0,-1,0,0,0,0,0,0,-1,0},
                {-1,0,0,4,0,0,0,4,0,-1,-1,0},
                {-1,0,0,-1,0,0,-1,-1,-1,2,-1,0},
                {-1,-1,0,-1,0,0,-1,0,0,0,-1,0},
                {-1,0,0,-1,0,0,-1,0,0,0,-1,0},
                {-1,0,2,-1,2,4,0,0,0,0,-1,0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0},
        };
        theMap = Arrays.stream(tmps).map(int[]::clone).toArray(int[][]::new);
        enemyPlayerMap = Arrays.stream(tmps).map(int[]::clone).toArray(int[][]::new);
        // Set the enemyPlayerMap
        for(int i = 0; i< theMap.length; i++) {
            for (int j = 0; j< theMap[0].length; j++) {
                if (theMap[i][j] == 2 || theMap[i][j] == 3) {
                    enemyPlayerMap[i][j] = 0;
                }
            }
        }

    }

    /**
     * Set the position of the special reward in the theMap
     * @param x x coordinate of the special reward
     * @param y y coordinate of the special reward
     */
    public void setSpecialReward(int x, int y) {
        theMap[x][y] = 3;
    }

    /**
     * Sets the position of the player in the theMap and the enemyPlayerMap
     * Player is denoted with 1, enemy is denoted with 2
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     */
    public void setPlayerPosition(int x, int y){
        for (int i = 0; i<enemyPlayerMap.length; i++) {
            for (int k = 0; k<enemyPlayerMap[0].length; k++) {
                if (enemyPlayerMap[i][k] == 1) {
                    enemyPlayerMap[i][k] = 0;
                }
                if (theMap[i][k] == 1) {
                    theMap[i][k] = 0;
                }
            }
        }
        enemyPlayerMap[x][y] = 1;
        theMap[x][y] = 1;
    }

    /**
     * Sets the position of the enemy in the theMap and the enemyPlayerMap
     * Player is denoted with 1, enemy is denoted with 2
     * @param x x coordinate of the enemy
     * @param y y coordinate of the enemy
     */
    public void setEnemyPosition(int x, int y){
        for (int i = 0; i<enemyPlayerMap.length; i++) {
            for (int k = 0; k<enemyPlayerMap[0].length; k++) {
                if (enemyPlayerMap[i][k] == 2) {
                    enemyPlayerMap[i][k] = 0;
                    break;
                }
            }
        }
        enemyPlayerMap[x][y] = 2;
    }

    /**
     * Updates the regular map to a new tracking system for moving entities, and set the enemyPlayerMap
     * Player is denoted with 1, enemy is denoted with 2.
     */
    public void update(){
        // Copy the regular map to a new tracking system for the enemy and player
        // Set the enemyPlayerMap
        for(int i = 0; i< theMap.length; i++) {
            for (int j = 0; j< theMap[0].length; j++) {
                if (theMap[i][j] == 2 || theMap[i][j] == 3) {
                    enemyPlayerMap[i][j] = 0;
                }
            }
        }
    }

    /**
     * Gets the int 2D array that representing the game map
     * @return the int 2D array that representing the game map
     */
    public int[][] getTheMap() {
        return theMap;
    }

    /**
     * Sets the int 2D array to a new enemy map representing the positions of the enemy and player
     * @param newMap The new game map
     */
    public void setTheEnemyMap(int[][] newMap) {
        this.enemyPlayerMap = newMap;
    }

    /**
     * Get the int 2D array of the map for the position of enemy and player
     * @return the int 2D array of the map for the position of enemy and player
     */
    public int[][] getEnemyPlayerMap() { return enemyPlayerMap;}
}
