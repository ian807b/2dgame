package com.groupNine.app;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class endingCellTest {

    @Mock gameWindow gameWin;
    endingCell endCell = new endingCell(gameWin);

    @org.junit.jupiter.api.Test
    public void testStart() {
        if (endCell.isShowEnding() != false){
            assert(false);
        }
        else{
            assert(true);
        }
    }

    @org.junit.jupiter.api.Test
    public void testAppearance() {
        endCell.setUpEndingCell();
        if (endCell.isShowEnding() != false){
            assert(true);
        }
        else{
            assert(false);
        }
    }

    @org.junit.jupiter.api.Test
    public void testDraw() {
        assertNotNull(endCell.getImage(), "Imaged loaded Properly");
    }

    @org.junit.jupiter.api.Test
    public void valuesTest() {
        endCell.setUpEndingCell();
        if (endCell.xGrid == 14 && endCell.yGrid == 9 ){
            assert(true);
        }
        else{
            assert(false);
        }
    }

}
