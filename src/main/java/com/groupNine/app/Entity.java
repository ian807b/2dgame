package com.groupNine.app;

/**
 * Abstract Entity class for any instances with initial position
 * @author Alice
 * @version 1.0
 */
public abstract class Entity {
    /**
     * x and y coordinates
     */
    int xGrid, yGrid;
    String direction;

    /**
     * Shows if an Entity instance is created for the first time
     */
    boolean firstTime = true;

    /**
     * Getter for the variable String direction
     * @return The direction of the Entity
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Getter for the variable int xGrid
     * @return The x coordinate of the Entity
     */
    public int getxGrid() {
        return xGrid;
    }

    /**
     * Getter for the variable int yGrid
     * @return The y coordinate of the Entity
     */
    public int getyGrid() {
        return yGrid;
    }
}
