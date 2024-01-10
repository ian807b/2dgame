package com.groupNine.app;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import javax.swing.*;

import static org.junit.Assert.assertNotNull;

public class punishmentListTest {

    @Mock JFrame tmpFrame;

    @org.junit.jupiter.api.Test
    public void testConstructor(){
        gameWindow gameWin = new gameWindow(tmpFrame);
        gameWin.map = new gameMap(gameWin);
        gameWin.punishmentList = new PunishmentList(gameWin);
        // There should exist multiple punishments
        assert (gameWin.punishmentList.punishmentArrayList.size() > 0);
    }

    @org.junit.jupiter.api.Test
    public void checkImage(){
        gameWindow gameWin = new gameWindow(tmpFrame);
        gameWin.map = new gameMap(gameWin);
        gameWin.punishmentList = new PunishmentList(gameWin);
        // The image should exist
        for (int i = 0; i<gameWin.punishmentList.punishmentArrayList.size(); i++){
            Assertions.assertNotNull(gameWin.punishmentList.punishmentArrayList.get(i).image);
        }
    }
}
