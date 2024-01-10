package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * A moving enemy which tracks the player constantly
 * @author Alice
 * @version 1.0
 * @see com.groupNine.app.Moving
 */
public class Enemy extends Moving{
    private gameWindow gameWin;
    /**
     * State of if the enemy has touched the player
     */
    private boolean touchedPlayer;

    /**
     * Sets up the xGrid and yGrid position of the enemy.
     * Also sets all of the images for the enemy.
     * @param gameWin The gameWindow of the game
     */
    public Enemy(gameWindow gameWin) {
        this.gameWin = gameWin;
        this.touchedPlayer = false;

        // Set the grid position
        this.xGrid = 8;
        this.yGrid = 8;

        try{
            upOne = ImageIO.read(getClass().getResourceAsStream("/enemy/up1.png"));
            upTwo = ImageIO.read(getClass().getResourceAsStream("/enemy/up2.png"));
            downOne = ImageIO.read(getClass().getResourceAsStream("/enemy/down1.png"));
            downTwo = ImageIO.read(getClass().getResourceAsStream("/enemy/down2.png"));
            leftOne = ImageIO.read(getClass().getResourceAsStream("/enemy/left1.png"));
            leftTwo = ImageIO.read(getClass().getResourceAsStream("/enemy/left2.png"));
            rightOne = ImageIO.read(getClass().getResourceAsStream("/enemy/right1.png"));
            rightTwo = ImageIO.read(getClass().getResourceAsStream("/enemy/right2.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        direction = "left";
    }

    /**
     * Gets the boolean value of touchedPlayer
     * @return touchedPlayer
     */
    public boolean isTouchedPlayer() {
        return touchedPlayer;
    }

    /**
     * Update the position according to the moving direction
     * with the shortest possible distance.
     * @param path an integer value represents the moving direction with the shortest distance
     */
    public void closestPosition(int path){
        switch (path) {
            case 1: // up
                yGrid -= 1;
                direction = "up";
                break;
            case 2: // down
                yGrid += 1;
                direction = "down";
                break;
            case 3: // left
                xGrid -= 1;
                direction = "left";
                break;
            case 4: // right
                xGrid += 1;
                direction = "right";
                break;
        }
    }

    /**
     * Updates the position of the enemy to place itself closer to the enemy.
     */
    public void update(){

        int path = pathExists(gameWin.map.getEnemyPlayerMap());

        closestPosition(path);

        if (gameWin.player.xGrid == this.xGrid && gameWin.player.yGrid == this.yGrid) {
            touchedPlayer = true;
        }

        gameWin.map.setEnemyPosition(xGrid, yGrid);
    }

    /**
     * Uses a breadth first search to find the distance to the player
     * @param grid 2D array of the gameMap
     * @param xPos XGrid position of the enemy
     * @param yPos YGrid position of the enemy
     * @return int The shortest distance to reach the player
     */
    public int BFS(int[][] grid, int xPos, int yPos) {

        int gridX = grid.length;
        int gridY = grid[0].length;
        boolean[][] visited = new boolean[gridX][gridY];
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(xPos, yPos, 0));

        while (queue.isEmpty() == false) {
            Node x = queue.remove();
            int row = x.x;
            int col = x.y;
            int len = x.dist;

            if (row < 0 || col < 0 || row > gridX || col > gridY || visited[row][col]) {
                continue;
            }

            visited[row][col] = true;

            if (grid[row][col] == 1) {
                return len;
            }

            if (col-1 >= 0 && grid[row][col - 1] != -1) {
                queue.add(new Node(row, col - 1, len + 1)); // go left
            }
            if (col+1 < gameWin.maxScreenRow && grid[row][col + 1] != -1) {
                queue.add(new Node(row, col + 1, len + 1)); // go right
            }
            if (row-1 >= 0 && grid[row - 1][col] != -1) {
                queue.add(new Node(row - 1, col, len + 1)); // go up
            }
            if (row+1 < gameWin.maxScreenCol && grid[row + 1][col] != 1) {
                queue.add(new Node(row + 1, col, len + 1)); // go down
            }
        }

        return -1;
    }

    /**
     * Checks if a path exists and returns which direction to move for the
     * shortest possible distance. (1 is up, 2 is down, 3 is left, 4 is right)
     * @param matrix 2D array of the gameMap
     * @return int Returns a value for which direction to move
     */
    public int pathExists(int[][] matrix) {
        int shortestPath = -1;
        int direction = 0;

        int enX = xGrid;
        int enY = yGrid;


        if (matrix[enX][enY - 1] != -1) { // Up
            if (BFS(matrix, enX, enY - 1) >= 0) {
                shortestPath = BFS(matrix, enX, enY - 1);
                direction = 1;
            } else if (shortestPath == -1 && BFS(matrix, enX, enY - 1) >= 0) {
                shortestPath = BFS(matrix, enX, enY - 1);
                direction = 1;
            }
        }
        if (matrix[enX][enY + 1] != -1) { // Down
            if (BFS(matrix, enX, enY + 1) < shortestPath && BFS(matrix, enX, enY + 1) >= 0) {
                shortestPath = BFS(matrix, enX, enY + 1);
                direction = 2;
            } else if (shortestPath == -1 && BFS(matrix, enX, enY + 1) >= 0) {
                shortestPath = BFS(matrix, enX, enY + 1);
                direction = 2;
            }
        }
        if (matrix[enX - 1][enY] != -1) { // Left
            if (BFS(matrix, enX - 1, enY) < shortestPath && BFS(matrix, enX - 1, enY) >= 0) {
                shortestPath = BFS(matrix, enX - 1, enY);
                direction = 3;
            } else if (shortestPath == -1 && BFS(matrix, enX - 1, enY) >= 0) {
                shortestPath = BFS(matrix, enX - 1, enY);
                direction = 3;
            }
        }
        if (matrix[enX + 1][enY] != -1) { // Right
            if (BFS(matrix, enX + 1, enY) < shortestPath && BFS(matrix, enX + 1, enY) >= 0) {
                shortestPath = BFS(matrix, enX + 1, enY);
                direction = 4;
            } else if (shortestPath == -1 && BFS(matrix, enX + 1, enY) >= 0) {
                shortestPath = BFS(matrix, enX + 1, enY);
                direction = 4;
            }
        }
        return direction;
    }


    /**
     * Illustrates the enemy onto the screen
     * @param graphic Used to draw onto the screen
     */
    public void draw(Graphics2D graphic) {
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = upTwo;
                }
                else{
                    image = upOne;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = downTwo;
                }
                else{
                    image = downOne;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = leftTwo;
                }
                else{
                    image = leftOne;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = rightTwo;
                }
                else{
                    image = rightOne;
                }
                if (firstTime == true){
                    image = rightOne;
                    firstTime = false;
                }
                break;
        }

        graphic.drawImage(image, this.xGrid*gameWin.tileSize, this.yGrid*gameWin.tileSize, gameWin.tileSize, gameWin.tileSize, null);

    }

    /**
     * Simple node that contains a x value, y value, and distance from source
     * @author Milo
     * @version 1.0
     */
    class Node {
        public int x;
        public int y;
        public int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

}
