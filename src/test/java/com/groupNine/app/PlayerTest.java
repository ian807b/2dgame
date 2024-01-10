package com.groupNine.app;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  @org.junit.jupiter.api.Test
  void changeScore() {
    // Instances needed to test the method.
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    assertEquals(50, gw.player.getScore()); // Tests default score.
    gw.player.changeScore(50); // Increases the score by 50.
    assertEquals(100, gw.player.getScore()); // Tests if the score is updated.
  }

  @org.junit.jupiter.api.Test
  void setDefaultValues() {
    // Instances needed to test the method.
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    handleInput hi = new handleInput();
    Player player = new Player(gw, hi);

    // Checks default values are correctly set.
    assertEquals(2, player.getxGrid());
    assertEquals(2, player.getyGrid());
    assertEquals(50, player.getScore());
    assertEquals("right", player.getDirection());
    assertEquals(0, player.getTime());
  }

  @org.junit.jupiter.api.Test
  void getPlayerImage() {
    // Instances needed to test the method.
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    // Checks if the images are properly loaded.
    assertNotNull(gw.player.upOne, "Down image properly loaded");
    assertNotNull(gw.player.downOne, "Down image properly loaded");
    assertNotNull(gw.player.leftOne, "Down image properly loaded");
    assertNotNull(gw.player.rightOne, "Down image properly loaded");

    assertNotNull(gw.player.upTwo, "Down image properly loaded");
    assertNotNull(gw.player.downTwo, "Down image properly loaded");
    assertNotNull(gw.player.leftTwo, "Down image properly loaded");
    assertNotNull(gw.player.rightTwo, "Down image properly loaded");
  }

  @org.junit.jupiter.api.Test
  void update() {
    // Instances needed to test the method.
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    // Checks false condition; the method shouldn't do anything.
    assertEquals("right", gw.player.getDirection());
    assertEquals(50, gw.player.getScore());
    assertEquals(0, gw.player.spriteCounter);

    // Checks when VK_UP is pressed.
    KeyEvent keyUp =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
    gw.inpHandler.keyPressed(keyUp); // Presses VK_UP.
    gw.player.update();
    assertEquals("up", gw.player.getDirection());
    gw.player.yGrid++; // Moves the player back to its initial position for further testing.
    gw.player.spriteCounter--;

    // Checks if the player on top of a regular reward.
    gw.map.getTheMap()[gw.player.xGrid][gw.player.yGrid - 1] = 2; // -1
    regularReward rw = new regularReward(gw, 2, 1); // Place a regular reward at (2, 1).
    gw.rewardmanage.rewardList.add(
        0, rw); // Add the reward to the arraylist that tracks all the regular rewards.
    gw.player.update();

    assertEquals(
        100,
        gw.player.getScore()); // Checks the score is updated after collecting a regular reward.

    gw.player.yGrid++; // Moves the player back to its initial position for further testing.
    gw.player.spriteCounter--;

    // Checks if the player is on top of the special reward.
    gw.map.getTheMap()[gw.player.xGrid][gw.player.yGrid - 1] = 3; // Player on the special reward.
    gw.player.update();
    assertEquals(200, gw.player.getScore());
    assertFalse(
        gw.specReward
            .isCanSpawn()); // Checks if the special reward has been removed after collection.

    gw.player.yGrid++; // Moves the player back to its initial position for further testing.
    gw.player.spriteCounter--;

    // Checks if the player is on top of a punishment.
    gw.map.getTheMap()[gw.player.xGrid][gw.player.yGrid - 1] = 4; // Player on top of a punishment.
    Punishment punish = new Punishment(gw, 2, 1); // Places a punishment on top of the player.
    gw.punishmentList.punishmentArrayList.add(0, punish); // Adds the punishment to the arraylist.
    gw.player.update();

    assertEquals(170, gw.player.getScore());

    // Checks the player position and spriteCounter have been updated.
    assertEquals(2, gw.player.getxGrid()); // Should be no change; the player only moves up by 1.
    assertEquals(1, gw.player.getyGrid()); // Checks if the player position has been updated.
    assertEquals(1, gw.player.spriteCounter);

    // Housekeeping
    gw.player.yGrid++; // Moves the player back to its initial position for further testing.
    gw.player.spriteCounter--;
    gw.inpHandler.keyReleased(keyUp); // Releases VK_UP for further testing.

    // Checks when VK_DOWN is pressed.
    gw.map.getTheMap()[gw.player.xGrid][gw.player.yGrid + 1] = 2;
    KeyEvent keyDown =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
    gw.inpHandler.keyPressed(keyDown);
    gw.player.update();
    assertEquals("down", gw.player.getDirection());
    gw.player.yGrid--; // Moves the player back to its initial position for further testing.
    gw.inpHandler.keyReleased(keyDown); // Releases VK_UP for further testing.

    // Checks when VK_LEFT is pressed.
    gw.map.getTheMap()[gw.player.xGrid - 1][gw.player.yGrid] = 2;
    KeyEvent keyLeft =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
    gw.inpHandler.keyPressed(keyLeft);
    gw.player.update();
    assertEquals("left", gw.player.getDirection());
    gw.player.xGrid++; // Moves the player back to its initial position for further testing.
    gw.inpHandler.keyReleased(keyLeft); // Releases VK_UP for further testing.

    // Checks when VK_RIGHT is pressed.
    gw.map.getTheMap()[gw.player.xGrid + 1][gw.player.yGrid] = 2;
    KeyEvent keyRight =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
    gw.inpHandler.keyPressed(keyRight);
    gw.player.update();
    assertEquals("right", gw.player.getDirection());
    gw.player.xGrid--; // Moves the player back to its initial position for further testing.
    gw.inpHandler.keyReleased(keyRight); // Releases VK_UP for further testing.
  }

  @org.junit.jupiter.api.Test
  void getScore() {
    // Instances needed to test the method.
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    assertEquals(50, gw.player.getScore());
  }

  @org.junit.jupiter.api.Test
  void touchingEndCell() {
    // Instances needed to test the method.
    JFrame window = new JFrame();
    gameWindow gw = new gameWindow(window);
    gw.run();

    // Checks false condition.
    assertFalse(
        gw.endCell.isShowEnding(),
        "Player touching cell malfunction; player is NOT touching the ending cell");

    // Checks when the game ends.
    gw.endCell.setUpEndingCell();
    gw.endCell.xGrid = gw.player.xGrid; // Position the player at the ending cell.
    gw.endCell.yGrid = gw.player.yGrid; // Position the player at the ending cell.
    assertTrue(gw.endCell.isShowEnding());
  }
}
