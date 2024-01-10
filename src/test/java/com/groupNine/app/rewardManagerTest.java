package com.groupNine.app;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class rewardManagerTest {

  @Test
  void constructorTest() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    // Should return 6 since 6 rewards have been set initially.
    assertEquals(6, gw.rewardmanage.rewardList.size());
  }

  @Test
  void claimRewardTest() {
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    // Should return 50 since testing the method with one of the rewards.
    assertEquals(
        50,
        gw.rewardmanage.claimReward(
            gw.rewardmanage.rewardList.get(0).getxGrid(),
            gw.rewardmanage.rewardList.get(0).getyGrid()));
    // Should return 0 since there is no reward in (0,0)
    assertEquals(0, gw.rewardmanage.claimReward(0, 0));
  }
}
