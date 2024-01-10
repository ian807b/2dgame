package com.groupNine.app;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class handleInputTest {
  handleInput hi = new handleInput();

  @org.junit.jupiter.api.Test
  void keyPressed() {
    assertFalse(hi.isUp());
    assertFalse(hi.isDown());
    assertFalse(hi.isLeft());
    assertFalse(hi.isRight());

    KeyEvent keyUp =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
    hi.keyPressed(keyUp);
    assertTrue(hi.isUp());

    KeyEvent keyDown =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
    hi.keyPressed(keyDown);
    assertTrue(hi.isDown());

    KeyEvent keyLeft =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
    hi.keyPressed(keyLeft);
    assertTrue(hi.isLeft());

    KeyEvent keyRight =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
    hi.keyPressed(keyRight);
    assertTrue(hi.isRight());
  }

  @org.junit.jupiter.api.Test
  void keyReleased() {
    KeyEvent keyUp =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
    hi.keyReleased(keyUp);
    assertFalse(hi.isUp());

    KeyEvent keyDown =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
    hi.keyReleased(keyDown);
    assertFalse(hi.isDown());

    KeyEvent keyLeft =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
    hi.keyReleased(keyLeft);
    assertFalse(hi.isLeft());

    KeyEvent keyRight =
        new KeyEvent(
            new Canvas(), KeyEvent.KEY_PRESSED, 1, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
    hi.keyReleased(keyRight);
    assertFalse(hi.isRight());
  }

  @org.junit.jupiter.api.Test
  void getterTest() {
    assertFalse(hi.isUp());
    assertFalse(hi.isDown());
    assertFalse(hi.isLeft());
    assertFalse(hi.isRight());
  }
}
