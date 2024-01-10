package com.groupNine.app;

import java.awt.image.BufferedImage;

/**
 * Stores the image of the backdrop and if the object can be collided with
 * @author Alice
 * @version 1.0
 */
public class wallImage {
    /**
     * Stores the image of the backdrop
     */
    public BufferedImage image;
    /**
     * Initialize to be false and assign true if needed to indicate that player can't go through
     */
    public boolean collision = false;
}
