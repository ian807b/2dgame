package com.groupNine.app;

import java.awt.image.BufferedImage;

/**
 * Abstract class which defines images for movement
 * @author Milo
 * @version 1.0
 * @see com.groupNine.app.Entity
 */
public abstract class Moving extends Entity{

    /**
     * BufferedImage variables for different facing directions of a moving object
     */
    public BufferedImage upOne, upTwo, downOne, downTwo, leftOne, leftTwo, rightOne, rightTwo;
    /**
     * Counter to keep track of moving objects` moving animations
     */
    public int spriteCounter = 0;
    /**
     * Each value (1 - 10) has an assigned image, facing different directions
     */
    public int spriteNum = 1;
}
