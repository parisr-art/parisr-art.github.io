package org.cis120;

/**
 * CIS 120 HW09 - TwentyFortyEight
 * (c) University of Pennsylvania
 * Created by Paris Rosen in Fall 2021.
 */

import org.cis120.twentyfortyeight.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.LinkedList;

/**
 * This class instantiates a TwentyFortyEight object, which is the model for the game.
 * As the user presses the arrow keys, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private org.cis120.TwentyFortyEight gameModel; // model for the game
    private LinkedList<Tile[][]> moves; // stores the move history (CONCEPT 2: COLLECTIONS)
    private LinkedList<Integer> scores; // stores the score history (CONCEPT 2: COLLECTIONS)
    private JLabel status; // current status text
    private boolean isPlaying; // sets if game is able to be played

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        gameModel = new org.cis120.TwentyFortyEight(); // initializes model for the game
        moves = new LinkedList<Tile[][]>(); // initializes a linked list to store moves
        scores = new LinkedList<Integer>(); // initializes a linked list to store scores
        status = statusInit; // initializes the status JLabel
        isPlaying = true; // initializes the ability to play the game

        // KeyListeners that handle events when arrow key is pressed
        addKeyListener((new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (isPlaying) {
                    Tile[][] boardCopy = gameModel.copyBoard();
                    moves.addFirst(boardCopy);
                    int scoreCopy = gameModel.getScore();
                    scores.addFirst(scoreCopy);
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        gameModel.mergeRight();
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        gameModel.mergeLeft();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        gameModel.mergeUp();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        gameModel.mergeDown();
                    }
                }
                gameEnd();
                repaint();
            }
        }));
    }

    // checks if the game has been won or lost (runs everytime an arrow key is pressed)
    public void gameEnd() {
        if (gameModel.checkWin()) {
            status.setText("You win!");
            isPlaying = false;
        } else if (gameModel.gameOver() && gameModel.checkEmptyTiles()) {
            boolean isGameOver = gameModel.gameOver();
            boolean isCheckEmptyTiles = gameModel.checkEmptyTiles();
            status.setText("You lose!");
            isPlaying = false;
        } else {
            return;
        }
    }

    // undos the last move (CONCEPT 2: COLLECTIONS)
    public void undoMove() {
        if (!moves.isEmpty()) {
            gameModel.board = moves.removeFirst();
            gameModel.score = scores.removeFirst();
            repaint();
        }
        requestFocusInWindow();
    }


    // saves the game to a file when save game button is clicked (CONCEPT 3: FILE IO)
    public void saveGame() {
        File file = new File("game_state.txt");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 4; r++) {
                    String stringValue = gameModel.board[c][r].getTileValue() + " ";
                    bw.write(stringValue);
                }
                bw.newLine();
            }
            bw.write(gameModel.score + "");
            bw.newLine();

            int numberMoves = moves.size();
            bw.write(numberMoves + "");
            bw.newLine();

            int counter = 0;
            for (Tile[][] move: moves) {
                for (int cc = 0; cc < 4; cc++) {
                    for (int rr = 0; rr < 4; rr ++) {
                        String secondStringValue = move[cc][rr].getTileValue() + " ";
                        bw.write(secondStringValue);
                    }
                    bw.newLine();
                }
                bw.write(scores.get(counter) + "");
                bw.newLine();
                counter++;
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    // reloads a saved game to the game board when load game button is clicked (CONCEPT 3: FILE IO)
    public void loadGame(String file) {
        moves.clear();
        scores.clear();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("game_state.txt"));

            for (int c = 0; c < 4; c++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int r = 0; r < 4; r++) {
                    gameModel.board[c][r].setTileValue(Integer.parseInt(numbers[r]));
                }
            }
            String scoreLine = br.readLine();
            gameModel.score = Integer.valueOf(scoreLine);

            int numberMoves = Integer.parseInt(br.readLine());
            for (int i = 0; i < numberMoves; i++) {
                Tile[][] newBoard = new Tile[4][4];
                for (int c = 0; c < 4; c++) {
                    String line = br.readLine();
                    String[] numbers = line.split(" ");
                    for (int r = 0; r < 4; r++) {
                        newBoard[c][r] = new Tile();
                        newBoard[c][r].setTileValue(Integer.parseInt(numbers[r]));
                    }
                }
                moves.add(newBoard);
                scores.add(Integer.parseInt(br.readLine()));
            }

            repaint();
            requestFocusInWindow();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Does Not Exist");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void gameReset() {
        gameModel.reset();
        gameModel.score = 0;
        isPlaying = true;
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }


    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameModel.checkWin()) {
            status.setText("You win!");
        } else if (gameModel.gameOver() && gameModel.checkEmptyTiles()) {
            status.setText("You lose!");
        } else {
            status.setText("Score: " + gameModel.getScore());
        }

        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(400, 0, 400, 400);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);
        g.drawLine(0, 400, 400, 400);

        Tile[][] board = gameModel.copyBoard();

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c ++) {
                int tileValue = board[r][c].getTileValue();
                String tileString = String.valueOf(tileValue);
                if (tileValue != 0) {
                    g.drawRect(c * 100, r * 100, 100, 100);
                    g.drawString(tileString, (c * 100) + 50, (r * 100) + 50);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
