package com.groupNine.app;

import javax.swing.*;
import org.mockito.Mock;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class gameMapTest {

    @Mock JFrame window = new JFrame();
    gameWindow gameWin = new gameWindow(window);
    gameMap gM = new gameMap(gameWin);

    Random rnd = new Random();
    int tmpX = rnd.nextInt(16);
    int tmpY = rnd.nextInt(12);

    @org.junit.jupiter.api.Test
    public void setSpecialReward() {
        gM.setSpecialReward(tmpX, tmpY);
        //check if TheMap[tmpX][tmpY] = 3
        assertEquals(3, gM.getTheMap()[tmpX][tmpY]);
    }

    @org.junit.jupiter.api.Test
    public void setPlayPosition(){
        gM.setPlayerPosition(tmpX, tmpY);
        //check if TheMap[i][k] == 1 && EnemyPlayerMap[i][k] == 1;
        assertEquals(1, gM.getEnemyPlayerMap()[tmpX][tmpY]);
        assertEquals(1, gM.getTheMap()[tmpX][tmpY]);
    }

    @org.junit.jupiter.api.Test
    public void setEnemyPosition(){
        gM.setEnemyPosition(tmpX, tmpY);
        //check if EnemyPlayerMap[tmpX][tmpY] == 2;
        assertEquals(2, gM.getEnemyPlayerMap()[tmpX][tmpY]);
    }

    @org.junit.jupiter.api.Test
    public void update(){
        gM.update();
        for (int i = 0; i < gM.getTheMap().length; i++){
            for (int j = 0; j < gM.getTheMap()[0].length; j++){
                if (gM.getTheMap()[i][j] == 2 && gM.getTheMap()[i][j] != 3){
                    assertEquals(0, gM.getEnemyPlayerMap()[i][j]);
                }
                if (gM.getTheMap()[i][j] == 3 && gM.getTheMap()[i][j] != 2){
                    assertEquals(0, gM.getEnemyPlayerMap()[i][j]);
                }
                if (gM.getTheMap()[i][j] == 2 && gM.getTheMap()[i][j] == 3){
                    assertEquals(0, gM.getEnemyPlayerMap()[i][j]);
                }
            }
        }
    }
}
