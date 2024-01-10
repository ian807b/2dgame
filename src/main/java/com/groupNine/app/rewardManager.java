package com.groupNine.app;

import java.awt.*;
import java.util.ArrayList;

/**
 * Places rewards and stores acquired reward points
 * @author Parmida
 * @version 1.0
 */
public class rewardManager {
    /**
     * Stores the regular awards into a list.
     */
    private gameWindow gameWin;
    ArrayList<regularReward> rewardList;

    /**
     * Scans the positions of the regular rewards and stores the objects of the regular rewards into an array.
     * @param gameWin gameWindow object to gain access to its variables.
     */
    public rewardManager(gameWindow gameWin) {
        this.gameWin = gameWin;
        rewardList = new ArrayList<regularReward>();
        // Save the regular rewards in a list to manage them later
        for (int i = 0; i<gameWin.maxScreenCol; i++) {
            for (int k = 0; k<gameWin.maxScreenRow; k++) {
                if (gameWin.map.getTheMap()[i][k] == 2) {
                    rewardList.add(new regularReward(gameWin, i, k));
                }

            }
        }
    }

    /**
     * Sums up the amount of the regular awards earned.
     * @param tmpX X-coordinate
     * @param tmpY Y-coordinate
     * @return The total amount of the regular awards earned.
     */
    public int claimReward(int tmpX, int tmpY) {
        int returnReward = 0;
        for (int i = 0; i<rewardList.size(); i++) {
            if (rewardList.get(i).xGrid == tmpX && rewardList.get(i).yGrid == tmpY){
                returnReward = rewardList.get(i).amount;
                rewardList.remove(i);
                break;
            }
        }
        return returnReward;
    }

    /**
     * Draws the regular awards at the coordinates stored in an array.
     * @param graphics Graphics2D object to draw the regular awards onto the map.
     */
    public void draw(Graphics2D graphics) {
        for (int i = 0; i<rewardList.size(); i++){
            rewardList.get(i).draw(graphics);
        }
    }
}
