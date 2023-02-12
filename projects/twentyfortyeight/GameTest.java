package org.cis120.twentyfortyeight;

import org.cis120.TwentyFortyEight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

// CONCEPT 4: JUNIT TESTING

public class GameTest {

    @Test
    public void mergeRight() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(1, 0).setTileValue(2);
        newGame.getTile(0, 1).setTileValue(4);
        newGame.getTile(2, 1).setTileValue(4);
        newGame.getTile(0, 2).setTileValue(8);
        newGame.getTile(1, 3).setTileValue(8);
        newGame.getTile(2, 3).setTileValue(4);
        newGame.getTile(0, 0).setTileValue(2);
        newGame.getTile(0, 3).setTileValue(0);
        newGame.getTile(1, 1).setTileValue(0);
        newGame.getTile(1, 2).setTileValue(0);
        newGame.getTile(2, 0).setTileValue(0);
        newGame.getTile(2, 2).setTileValue(0);
        newGame.getTile(3, 0).setTileValue(0);
        newGame.getTile(3, 1).setTileValue(0);
        newGame.getTile(3, 2).setTileValue(0);
        newGame.getTile(3, 3).setTileValue(0);
        newGame.mergeRight();
        assertEquals(newGame.getTile(0, 3).getTileValue(), 8);
        assertEquals(newGame.getTile(1, 3).getTileValue(), 8);
        assertEquals(newGame.getTile(2, 3).getTileValue(), 8);
        assertEquals(newGame.getTile(0, 2).getTileValue(), 4);
        assertEquals(newGame.getTile(0, 1).getTileValue(), 2);
        assertEquals(newGame.getTile(1, 2).getTileValue(), 2);
    }

    @Test
    public void mergeLeft() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(0);
        newGame.getTile(0, 1).setTileValue(2);
        newGame.getTile(0, 2).setTileValue(2);
        newGame.getTile(0, 3).setTileValue(2);
        newGame.getTile(1, 0).setTileValue(8);
        newGame.getTile(1, 1).setTileValue(4);
        newGame.getTile(1, 2).setTileValue(4);
        newGame.getTile(1, 3).setTileValue(0);
        newGame.getTile(2, 0).setTileValue(0);
        newGame.getTile(2, 1).setTileValue(0);
        newGame.getTile(2, 2).setTileValue(16);
        newGame.getTile(2, 3).setTileValue(0);
        newGame.getTile(3, 0).setTileValue(0);
        newGame.getTile(3, 1).setTileValue(0);
        newGame.getTile(3, 2).setTileValue(0);
        newGame.getTile(3, 3).setTileValue(0);
        newGame.mergeLeft();
        assertEquals(newGame.getTile(0, 0).getTileValue(), 4);
        assertEquals(newGame.getTile(0, 1).getTileValue(), 2);
        assertEquals(newGame.getTile(1, 0).getTileValue(), 8);
        assertEquals(newGame.getTile(1, 1).getTileValue(), 8);
        assertEquals(newGame.getTile(2, 0).getTileValue(), 16);
    }

    @Test
    public void mergeUp() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(0);
        newGame.getTile(0, 1).setTileValue(0);
        newGame.getTile(0, 2).setTileValue(2);
        newGame.getTile(0, 3).setTileValue(0);
        newGame.getTile(1, 0).setTileValue(0);
        newGame.getTile(1, 1).setTileValue(16);
        newGame.getTile(1, 2).setTileValue(0);
        newGame.getTile(1, 3).setTileValue(0);
        newGame.getTile(2, 0).setTileValue(0);
        newGame.getTile(2, 1).setTileValue(8);
        newGame.getTile(2, 2).setTileValue(0);
        newGame.getTile(2, 3).setTileValue(4);
        newGame.getTile(3, 0).setTileValue(0);
        newGame.getTile(3, 1).setTileValue(8);
        newGame.getTile(3, 2).setTileValue(2);
        newGame.getTile(3, 3).setTileValue(0);
        newGame.mergeUp();
        assertEquals(newGame.getTile(0, 1).getTileValue(), 16);
        assertEquals(newGame.getTile(0, 2).getTileValue(), 4);
        assertEquals(newGame.getTile(0, 3).getTileValue(), 4);
        assertEquals(newGame.getTile(1, 1).getTileValue(), 16);
    }

    @Test
    public void mergeDown() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(2);
        newGame.getTile(0, 1).setTileValue(0);
        newGame.getTile(0, 2).setTileValue(0);
        newGame.getTile(0, 3).setTileValue(8);
        newGame.getTile(1, 0).setTileValue(2);
        newGame.getTile(1, 1).setTileValue(0);
        newGame.getTile(1, 2).setTileValue(0);
        newGame.getTile(1, 3).setTileValue(16);
        newGame.getTile(2, 0).setTileValue(2);
        newGame.getTile(2, 1).setTileValue(0);
        newGame.getTile(2, 2).setTileValue(0);
        newGame.getTile(2, 3).setTileValue(0);
        newGame.getTile(3, 0).setTileValue(2);
        newGame.getTile(3, 1).setTileValue(0);
        newGame.getTile(3, 2).setTileValue(0);
        newGame.getTile(3, 3).setTileValue(0);
        newGame.mergeDown();
        assertEquals(newGame.getTile(2, 0).getTileValue(), 4);
        assertEquals(newGame.getTile(3, 0).getTileValue(), 4);
        assertEquals(newGame.getTile(2, 3).getTileValue(), 8);
        assertEquals(newGame.getTile(3, 3).getTileValue(), 16);
    }

    @Test
    public void checkEmptyTilesTrue() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(2);
        newGame.getTile(0, 1).setTileValue(2);
        newGame.getTile(0, 2).setTileValue(4);
        newGame.getTile(0, 3).setTileValue(16);
        newGame.getTile(1, 0).setTileValue(32);
        newGame.getTile(1, 1).setTileValue(2);
        newGame.getTile(1, 2).setTileValue(4);
        newGame.getTile(1, 3).setTileValue(16);
        newGame.getTile(2, 0).setTileValue(2);
        newGame.getTile(2, 1).setTileValue(64);
        newGame.getTile(2, 2).setTileValue(32);
        newGame.getTile(2, 3).setTileValue(2);
        newGame.getTile(3, 0).setTileValue(2);
        newGame.getTile(3, 1).setTileValue(8);
        newGame.getTile(3, 2).setTileValue(8);
        newGame.getTile(3, 3).setTileValue(2);
        assertTrue(newGame.checkEmptyTiles());
    }

    @Test
    public void checkEmptyTilesFalse() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(2);
        newGame.getTile(0, 1).setTileValue(0);
        newGame.getTile(0, 2).setTileValue(4);
        newGame.getTile(0, 3).setTileValue(16);
        newGame.getTile(1, 0).setTileValue(32);
        newGame.getTile(1, 1).setTileValue(2);
        newGame.getTile(1, 2).setTileValue(4);
        newGame.getTile(1, 3).setTileValue(16);
        newGame.getTile(2, 0).setTileValue(2);
        newGame.getTile(2, 1).setTileValue(64);
        newGame.getTile(2, 2).setTileValue(32);
        newGame.getTile(2, 3).setTileValue(2);
        newGame.getTile(3, 0).setTileValue(2);
        newGame.getTile(3, 1).setTileValue(0);
        newGame.getTile(3, 2).setTileValue(8);
        newGame.getTile(3, 3).setTileValue(2);
        assertFalse(newGame.checkEmptyTiles());
    }

    @Test
    public void gameOverTrue() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(4);
        newGame.getTile(0, 1).setTileValue(2);
        newGame.getTile(0, 2).setTileValue(4);
        newGame.getTile(0, 3).setTileValue(16);
        newGame.getTile(1, 0).setTileValue(2);
        newGame.getTile(1, 1).setTileValue(4);
        newGame.getTile(1, 2).setTileValue(8);
        newGame.getTile(1, 3).setTileValue(4);
        newGame.getTile(2, 0).setTileValue(4);
        newGame.getTile(2, 1).setTileValue(16);
        newGame.getTile(2, 2).setTileValue(4);
        newGame.getTile(2, 3).setTileValue(2);
        newGame.getTile(3, 0).setTileValue(64);
        newGame.getTile(3, 1).setTileValue(32);
        newGame.getTile(3, 2).setTileValue(2);
        newGame.getTile(3, 3).setTileValue(8);
        assertTrue(newGame.gameOver());
    }

    @Test
    public void gameOverFalse() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(4);
        newGame.getTile(0, 1).setTileValue(2);
        newGame.getTile(0, 2).setTileValue(4);
        newGame.getTile(0, 3).setTileValue(16);
        newGame.getTile(1, 0).setTileValue(2);
        newGame.getTile(1, 1).setTileValue(4);
        newGame.getTile(1, 2).setTileValue(8);
        newGame.getTile(1, 3).setTileValue(4);
        newGame.getTile(2, 0).setTileValue(4);
        newGame.getTile(2, 1).setTileValue(16);
        newGame.getTile(2, 2).setTileValue(4);
        newGame.getTile(2, 3).setTileValue(2);
        newGame.getTile(3, 0).setTileValue(64);
        newGame.getTile(3, 1).setTileValue(32);
        newGame.getTile(3, 2).setTileValue(2);
        newGame.getTile(3, 3).setTileValue(2);
        assertFalse(newGame.gameOver());
    }

    @Test
    public void checkWinTrue() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(4);
        newGame.getTile(0, 1).setTileValue(2);
        newGame.getTile(0, 2).setTileValue(4);
        newGame.getTile(0, 3).setTileValue(16);
        newGame.getTile(1, 0).setTileValue(2);
        newGame.getTile(1, 1).setTileValue(4);
        newGame.getTile(1, 2).setTileValue(8);
        newGame.getTile(1, 3).setTileValue(4);
        newGame.getTile(2, 0).setTileValue(4);
        newGame.getTile(2, 1).setTileValue(16);
        newGame.getTile(2, 2).setTileValue(4);
        newGame.getTile(2, 3).setTileValue(2);
        newGame.getTile(3, 0).setTileValue(2048);
        newGame.getTile(3, 1).setTileValue(32);
        newGame.getTile(3, 2).setTileValue(2);
        newGame.getTile(3, 3).setTileValue(2);
        assertTrue(newGame.checkWin());
    }

    @Test
    public void checkWinFalse() {
        TwentyFortyEight newGame = new TwentyFortyEight();
        newGame.getTile(0, 0).setTileValue(4);
        newGame.getTile(0, 1).setTileValue(2);
        newGame.getTile(0, 2).setTileValue(4);
        newGame.getTile(0, 3).setTileValue(16);
        newGame.getTile(1, 0).setTileValue(2);
        newGame.getTile(1, 1).setTileValue(4);
        newGame.getTile(1, 2).setTileValue(8);
        newGame.getTile(1, 3).setTileValue(4);
        newGame.getTile(2, 0).setTileValue(4);
        newGame.getTile(2, 1).setTileValue(16);
        newGame.getTile(2, 2).setTileValue(4);
        newGame.getTile(2, 3).setTileValue(2);
        newGame.getTile(3, 0).setTileValue(16);
        newGame.getTile(3, 1).setTileValue(32);
        newGame.getTile(3, 2).setTileValue(2);
        newGame.getTile(3, 3).setTileValue(2);
        assertFalse(newGame.checkWin());
    }
}
