package com.groupNine.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * The purpose of wallManager class is to take care of the location of barriers according to the game map,
 * and the collision between the wall and the player
 * @author Alice
 * @version 1.0
 */
public class wallManager {

    /**
     * The game window of the game
     */
    gameWindow gameWin;
    /**
     * Array stores all of the possible barriers on the gameMap
     */
    wallImage[] barriers;

    /**
     * Assigns the parameter gameWin to this gameWin,
     * while allowing only 3 types of barriers to be placed in the game
     * @param gameWin input the game Window of the game
     */
    public wallManager(gameWindow gameWin) {
        this.gameWin = gameWin;
        barriers = new wallImage[3];
        getWallImages();
    }

    /**
     * This method storing the water image to index 0 of barriers array.
     * Also, storing rock image to index 1 of barriers array and assign the collision of it to be true.
     */
    private void getWallImages() {
        try {
            barriers[0] = new wallImage();
            barriers[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            barriers[1] = new wallImage();
            barriers[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock.png"));
            barriers[1].collision = true;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method draw the water and rock on the game window according to the game map 2D array
     * @param graphics The graphical display on the game window
     */
    public void draw(Graphics2D graphics) {
        for (int i = 0; i<gameWin.maxScreenCol; i++) {
            for (int k = 0; k<gameWin.maxScreenRow; k++) {
                if (gameWin.map.getTheMap()[i][k] == -1) {
                    graphics.drawImage(barriers[0].image, i*(gameWin.tileSize), k*(gameWin.tileSize), gameWin.tileSize, gameWin.tileSize, null);
                    graphics.drawImage(barriers[1].image, i*(gameWin.tileSize), k*(gameWin.tileSize), gameWin.tileSize, gameWin.tileSize, null);
                }
                else {
                    graphics.drawImage(barriers[0].image, i*(gameWin.tileSize), k*(gameWin.tileSize), gameWin.tileSize, gameWin.tileSize, null);
                }
            }
        }
    }





}
