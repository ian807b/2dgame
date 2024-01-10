package com.groupNine.app;

import javax.swing.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

  @org.junit.jupiter.api.Test
  void constructorTest() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    Enemy enemy = new Enemy(gw);

    assertFalse(enemy.isTouchedPlayer());

    assertEquals(8, enemy.getxGrid());
    assertEquals(8, enemy.getyGrid());

    assertNotNull(enemy.upOne);
    assertNotNull(enemy.upTwo);
    assertNotNull(enemy.downOne);
    assertNotNull(enemy.downTwo);
    assertNotNull(enemy.leftOne);
    assertNotNull(enemy.leftTwo);
    assertNotNull(enemy.rightOne);
    assertNotNull(enemy.rightTwo);

    assertEquals("left", enemy.getDirection());
  }

  @org.junit.jupiter.api.Test
  void isTouchedPlayer() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    Enemy enemy = new Enemy(gw);

    assertFalse(enemy.isTouchedPlayer());
  }

  @org.junit.jupiter.api.Test
  void update() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.enemy = new Enemy(gw);
    gw.map = new gameMap(gw);
    gw.player = new Player(gw, new handleInput());
    gw.run();
    gw.enemy.xGrid = gw.player.xGrid;
    gw.enemy.yGrid = gw.player.yGrid;
    int[][] newMap = Arrays.stream(gw.map.getEnemyPlayerMap()).map(int[]::clone).toArray(int[][]::new);
    for(int i = 0; i< newMap.length; i++) {
      for (int k = 0; k<newMap[0].length; k++){
          newMap[i][k] = 0;
      }
    }
    gw.map.setTheEnemyMap(newMap);

    gw.enemy.xGrid = gw.player.xGrid+1;
    gw.enemy.yGrid = gw.player.yGrid;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    gw.enemy.update();
    assertTrue(gw.enemy.isTouchedPlayer());

    gw.enemy.xGrid = gw.player.xGrid-1;
    gw.enemy.yGrid = gw.player.yGrid;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    gw.enemy.update();
    assertTrue(gw.enemy.isTouchedPlayer());

    gw.enemy.xGrid = gw.player.xGrid;
    gw.enemy.yGrid = gw.player.yGrid+1;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    gw.enemy.update();
    assertTrue(gw.enemy.isTouchedPlayer());

    gw.enemy.xGrid = gw.player.xGrid;
    gw.enemy.yGrid = gw.player.yGrid-1;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    gw.enemy.update();
    assertTrue(gw.enemy.isTouchedPlayer());
  }

  @org.junit.jupiter.api.Test
  void BFS() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    Enemy enemy = new Enemy(gw);

    int[][] gridTest = new int[16][12];
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 12; j++) {
        gridTest[i][j] = 0;
      }
    }
    gridTest[15][11] = 1;
    assertEquals(22, enemy.BFS(gridTest, 2, 2));
  }

  @org.junit.jupiter.api.Test
  void pathExists() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.enemy = new Enemy(gw);
    gw.map = new gameMap(gw);
    gw.player = new Player(gw, new handleInput());
    gw.run();
    gw.enemy.xGrid = gw.player.xGrid;
    gw.enemy.yGrid = gw.player.yGrid;
    int[][] newMap = Arrays.stream(gw.map.getEnemyPlayerMap()).map(int[]::clone).toArray(int[][]::new);
    for (int i = 0; i < newMap.length; i++) {
      for (int k = 0; k < newMap[0].length; k++) {
        newMap[i][k] = 0;
      }
    }
    gw.map.setTheEnemyMap(newMap);

    gw.enemy.xGrid = gw.player.xGrid + 1;
    gw.enemy.yGrid = gw.player.yGrid;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    assertEquals(3, gw.enemy.pathExists(gw.map.getEnemyPlayerMap()));

    gw.enemy.xGrid = gw.player.xGrid - 1;
    gw.enemy.yGrid = gw.player.yGrid;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    assertEquals(4, gw.enemy.pathExists(gw.map.getEnemyPlayerMap()));

    gw.enemy.xGrid = gw.player.xGrid;
    gw.enemy.yGrid = gw.player.yGrid + 1;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    assertEquals(1, gw.enemy.pathExists(gw.map.getEnemyPlayerMap()));

    gw.enemy.xGrid = gw.player.xGrid;
    gw.enemy.yGrid = gw.player.yGrid - 1;
    gw.map.setEnemyPosition(gw.enemy.xGrid, gw.enemy.yGrid);
    gw.map.setPlayerPosition(gw.player.xGrid, gw.player.yGrid);
    assertEquals(2, gw.enemy.pathExists(gw.map.getEnemyPlayerMap()));
  }

}
