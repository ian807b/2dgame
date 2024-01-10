package com.groupNine.app;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class regularRewardTest {
  @Mock JFrame window = new JFrame();
  @Mock gameWindow gw = new gameWindow(window);
  int x = 10;
  int y = 10;
  regularReward rR = new regularReward(gw, x, y);

  @Test
  void constructorTest() {
    assertEquals(gw, this.gw);
    assertEquals(50, rR.amount);
    assertEquals(10, rR.getxGrid());
    assertEquals(10, rR.getyGrid());
    assertNotNull(rR.image);
  }
}
