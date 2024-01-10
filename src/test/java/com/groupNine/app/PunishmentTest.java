package com.groupNine.app;

import org.mockito.Mock;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PunishmentTest {

    @Mock gameWindow gameWin;

    Random rnd = new Random();
    int tmpX = rnd.nextInt(16);
    int tmpY = rnd.nextInt(12);

    Punishment punishment = new Punishment(gameWin, tmpX, tmpY);

    @org.junit.jupiter.api.Test
    public void constructorTest(){
        assertEquals(tmpX, punishment.getxGrid());
        assertEquals(tmpY, punishment.getyGrid());
        assertEquals(gameWin, this.gameWin);
        assertEquals(-30, punishment.getDamage());
        assertNotNull(punishment.image);
    }

    @org.junit.jupiter.api.Test
    public void getDamage(){
        assertEquals(-30, punishment.getDamage());
    }

    @org.junit.jupiter.api.Test
    public void testDraw(){
        assertNotNull(punishment.getImage(), "image loaded properly");
    }
}
