package com.groupNine.app;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class gameWindowTest {
  @Test
  void startGameThread() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);

    gw.startGameThread();
    assertNotNull(gw.gameThread);
  }

  @Test
  void startGameStage() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);

    // Default conditions
    assertFalse(gw.gameEnd);
    assertNull(gw.endScreenTimer);
  }

  @Test
  void playerWinLose() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);

    gw.playerWinLose();

    assertTrue(gw.gameEnd);
    assertNotNull(gw.endScreenTimer);
  }

  @Test
  void run() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();
    assertNotNull(gw.inpHandler);
    assertNotNull(gw.map);
    assertNotNull(gw.walls);
    assertNotNull(gw.rewardmanage);
    assertNotNull(gw.specReward);
    assertNotNull(gw.punishmentList);
    assertNotNull(gw.endCell);
    assertNotNull(gw.player);
    assertNotNull(gw.enemy);
    assertNotNull(gw.timer);
    assertNotNull(gw.screenEnd);
  }
}
