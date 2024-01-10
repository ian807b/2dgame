package com.groupNine.app;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;


class specialRewardTest {

  @Test
  void constructorTest() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    specialReward sr = new specialReward(gw);

    assertEquals(100, sr.amount);
    assertNotNull(sr.image);
    assertFalse(sr.isCanSpawn());
  }

  @Test
  void initiateSpawn() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();
    gw.specReward.initiateSpawn();
    assertTrue(gw.specReward.isCanSpawn());
    assertNotEquals(0, gw.map.getTheMap()[gw.specReward.getxGrid()][gw.specReward.getyGrid()]);
    assertTrue(gw.specReward.getyGrid() <= gw.maxScreenRow);
    assertEquals(3, gw.map.getTheMap()[gw.specReward.getxGrid()][gw.specReward.getyGrid()]);
  }

  @Test
  void removeReward() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();
    gw.specReward.xGrid = 10;
    gw.specReward.yGrid = 10;

    gw.map.getTheMap()[gw.specReward.getxGrid()][gw.specReward.getyGrid()] = 3;
    gw.specReward.removeReward();

    assertFalse(gw.specReward.isCanSpawn());
    assertEquals(0, gw.map.getTheMap()[gw.specReward.getxGrid()][gw.specReward.getyGrid()]);
  }
}

