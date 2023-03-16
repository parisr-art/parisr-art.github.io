package org.cis120;

/**
 * CIS 120 HW09 - TwentyFortyEight
 * (c) University of Pennsylvania
 * Created by Paris Rosen in Fall 2021.
 */

import org.cis120.twentyfortyeight.Tile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is a model for TwentyFortyEight.
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games. We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec36.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of 2048,
 * visualized with Strings printed to the console.
 */
public class TwentyFortyEight {

    public Tile[][] board = new Tile [4][4]; //(CONCEPT 1: 2D ARRAY)
    public int score;

    /**
     * Constructor sets up game state.
     */
    public TwentyFortyEight() {
        reset();
    }

    // returns tile at a given location in array (used for JUnit testing purposes)
    public Tile getTile(int c, int r) {
        Tile t = board[c][r];
        return t;
    }

    // returns score
    public int getScore() {
        return score;
    }

    // sets score
    private void setScore(int s) {
        score = s;
    }

    // returns copy of current board
    public Tile[][] copyBoard() {
        Tile[][] newBoard = new Tile[4][4];
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                newBoard[c][r] = new Tile(board[c][r].getTileValue());
            }
        }
        return newBoard;
    }

    // merges two tiles together
    public void merge(Tile x, Tile y) {
        int xValue = x.getTileValue();
        int yValue = y.getTileValue();
        if (xValue == yValue) {
            x.setTileValue(yValue * 2);
            y.setTileValue(0);
            score += (xValue * 2);
        }
    }

    // moves Tiles to the right
    public void moveRight() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                int value = board[c][3 - r].getTileValue();
                for (int i = 4 - r; i < 4; i++) {
                    if (board[c][i].getTileValue() == 0) {
                        board[c][i].setTileValue(value);
                        board[c][i - 1].setTileValue(0);
                    }
                }
            }
        }
    }

    // moves Tiles to the right, merges values (and moves merged values),
    // and adds a 2/4 Tile to board
    public void mergeRight() {
        moveRight();
        for (int c = 0; c < 4; c ++) {
            for (int r = 0; r < 3; r ++) {
                merge(board[c][2 - r], board [c][3 - r]);
            }
        }
        moveRight();
        generateTile();
    }

    // moves Tiles to the left
    public void moveLeft() {
        for (int c = 0; c < 4; c ++) {
            for (int r = 0; r < 4; r++) {
                int value = board[c][r].getTileValue();
                for (int i = r - 1; i >= 0; i--) {
                    if (board[c][i].getTileValue() == 0) {
                        board[c][i].setTileValue(value);
                        board[c][i + 1].setTileValue(0);
                    }
                }
            }
        }
    }

    // moves Tiles to the left, merges values (and moves merged values),
    // and adds a 2/4 Tile to board
    public void mergeLeft() {
        moveLeft();
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                merge(board[c][r + 1], board[c][r]);
            }
        }
        moveLeft();
        generateTile();
    }

    // moves Tiles up
    public void moveUp() {
        for (int r = 0; r < 4; r ++) {
            for (int c = 0; c < 4; c++) {
                int value = board[c][r].getTileValue();
                for (int i = c - 1; i >= 0; i--) {
                    if (board[i][r].getTileValue() == 0) {
                        board[i][r].setTileValue(value);
                        board[i + 1][r].setTileValue(0);
                    }
                }
            }
        }
    }

    // moves Tiles up, merges values (and moves merged values), and adds a 2/4 Tile to the board
    public void mergeUp() {
        moveUp();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                merge(board[c + 1][r], board[c][r]);
            }
        }
        moveUp();
        generateTile();
    }

    // moves Tiles down
    public void moveDown() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                int value = board[3 - c][r].getTileValue();
                for (int i = 4 - c; i < 4; i++) {
                    if (board[i][r].getTileValue() == 0) {
                        board[i][r].setTileValue(value);
                        board[i - 1][r].setTileValue(0);
                    }
                }
            }
        }
    }

    // moves Tiles down, merges values (and moves merged values), and adds a 2/4 Tile to the board
    public void mergeDown() {
        moveDown();
        for (int r = 0; r < 4; r ++) {
            for (int c = 0; c < 3; c ++) {
                merge(board[2 - c][r], board [3 - c][r]);
            }
        }
        moveDown();
        generateTile();
    }

    // creates a board with all Tiles set to 0
    public void createNewBoard() {
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                board[c][r] = new Tile();
            }
        }
    }

    // changes the value of Tile from 0 to either 2 or 4
    public void generateTile() {
        int value = 0;
        double randomeValue = Math.random();
        if (randomeValue < 0.8) {
            value = 2;
        } else {
            value = 4;
        }

        LinkedList<int[]> zeros = new LinkedList<>();

        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r ++) {
                if (board[c][r].getTileValue() == 0) {
                    int[] coordinates = {c, r};
                    zeros.add(coordinates);
                }
            }
        }

        if (!zeros.isEmpty()) {
            int[] generatedTile = zeros.get((int) Math.floor(Math.random() * zeros.size()));
            board[generatedTile[0]][generatedTile[1]].setTileValue(value);
        }
    }

    // checks to see if the game board contains any zeros (empty tiles) -- (lose condiiton)
    public boolean checkEmptyTiles() {
        boolean isZeros = true;
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (board[c][r].getTileValue() == 0) {
                    isZeros = false;
                }
            }
        }
        return isZeros;
    }

    // checks to see if any Tiles can still be merged together (lose condition)
    public boolean gameOver() {
        boolean movesAvaliable = true;
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                List<Integer> adjacentTiles = new ArrayList<Integer>();
                if (c + 1 <= 3) {
                    adjacentTiles.add(board[c + 1][r].getTileValue());
                }
                if (c - 1 >= 0) {
                    adjacentTiles.add(board[c - 1][r].getTileValue());
                }
                if (r + 1 <= 3) {
                    adjacentTiles.add(board[c][r + 1].getTileValue());
                }
                if (r - 1 >= 0) {
                    adjacentTiles.add(board[c][r - 1].getTileValue());
                }
                for (int tileValue: adjacentTiles) {
                    if (board[c][r].getTileValue() == tileValue) {
                        movesAvaliable = false;
                    }
                }
            }
        }
        return movesAvaliable;
    }

    // checks to see if board contains Tile with value of 2048 (win condition)
    public boolean checkWin() {
        boolean win = false;
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (getTile(c,r).getTileValue() == 2048) {
                    win = true;
                }
            }
        }
        return win;
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("\n\nScore: " + score + "\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getTileValue());
                if (j < 3) {
                    System.out.print(" | ");
                }
            }
            if (i < 3) {
                System.out.println("\n--------------");
            }
        }
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new Tile[4][4];
        score = 0;
        createNewBoard();
        generateTile();
        generateTile();
    }


    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
        TwentyFortyEight t = new TwentyFortyEight();
        t.printGameState();
        t.mergeUp();
        t.printGameState();
        t.mergeRight();
        t.printGameState();
        t.mergeLeft();
        t.printGameState();
        t.mergeDown();
        t.printGameState();
    }
}
