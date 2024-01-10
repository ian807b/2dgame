package com.groupNine.app;

import java.awt.*;
import java.util.ArrayList;

/**
 * Set up the punishments according to the game map
 * @author Ian
 * @version 1.0
 */
public class PunishmentList {
    /**
     * Stores all the visible punishments
     */
    public ArrayList<Punishment> punishmentArrayList;
    private gameWindow gameWin;

    /**
     * Initialize this punishmentArrayList,
     * assign the parameter gameWin to this gameWin,
     * and call the setup method
     * @param gameWin The gameWindow of the game
     */
    public PunishmentList(gameWindow gameWin) {
        this.punishmentArrayList = new ArrayList<>();
        this.gameWin = gameWin;
        setup();
    }

    /**
     * This method go through the game map and store a punishment into
     * punishmentArrayList wherever there is a punishment
     */
    private void setup() {
        for (int i = 0; i<gameWin.map.getTheMap().length; i++) {
            for(int k = 0; k<gameWin.map.getTheMap()[0].length; k++) {
                if (gameWin.map.getTheMap()[i][k] == 4) {
                    punishmentArrayList.add(new Punishment(gameWin, i, k));
                }
            }
        }
    }

    /**
     * This method draw the fishing hook on the game window according to the game map 2D array
     * @param graphic the graphical display on the game window
     */
    public void draw(Graphics2D graphic) {
        for (int i = 0; i<punishmentArrayList.size(); i++) {
            punishmentArrayList.get(i).draw(graphic);
        }
    }
}
