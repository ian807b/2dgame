package com.groupNine.app;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class wallManagerTest {

    @Mock gameWindow gameWin;
    wallManager wall_manager = new wallManager(gameWin);

    @org.junit.jupiter.api.Test
    public void getWallImages() {
        assertNotNull(wall_manager.barriers[0].image, "Water image properly loaded");
        assertNotNull(wall_manager.barriers[1].image, "Rock image properly loaded");
        assertTrue(wall_manager.barriers[1].collision);
        assertFalse(wall_manager.barriers[0].collision);
    }
}
