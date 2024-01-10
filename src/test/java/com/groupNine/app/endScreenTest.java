package com.groupNine.app;

import org.mockito.Mock;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class endScreenTest {
    @Mock JFrame tmpFrame = new JFrame();
    gameWindow gameWin = new gameWindow(tmpFrame);
    endScreen screenEnd = new endScreen(gameWin);

    @org.junit.jupiter.api.Test
    public void imageTest() {
        assertNotNull(screenEnd.getImage(), "Imaged loaded Properly");
    }

    @org.junit.jupiter.api.Test
    public void firstTimeTest() {
        if (screenEnd.isFirstTime() == true){
            assert(true);
        }
        else{
            assert(false);
        }
    }

    @org.junit.jupiter.api.Test
    public void gameStateTest() {
        screenEnd.setWinLoseState(true);
        if (screenEnd.isWinLoseState() == true){
            assert(true);
        }
        else{
            assert(false);
        }
        screenEnd = new endScreen(gameWin);
        screenEnd.setWinLoseState(false);
        if (screenEnd.isWinLoseState() == false){
            assert(true);
        }
        else{
            assert(false);
        }

    }

    @org.junit.jupiter.api.Test
    public void playerEndScreenTest() {
        // Setup variables to use
        gameWindow tmpGame = new gameWindow(tmpFrame);
        tmpGame.startGameThread();
        handleInput tmpHandler = new handleInput();
        tmpGame.player = new Player(tmpGame, tmpHandler);
        tmpGame.screenEnd = new endScreen(tmpGame);
        // Set an example start time
        tmpGame.player.changeTime(-1);
        // Save the data inside screen end
        tmpGame.screenEnd.setWinLoseState(true);
        // Check for a positive game time
        if(tmpGame.screenEnd.getEndTime() >= 0) {
            assert(true);
        }
        else{
            assert(false);
        }
    }


}
