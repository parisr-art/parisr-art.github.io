=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays - The game board is stored as a 2D array of Tiles. This is an appropriate use of the concept
                 because the game board is a 4 x 4 grid that can be represented by the rows and columns of a 2D array.

  2. Collections - I used a Tile linked list to store the move history and an integer linked list to store the score
                   history. Everytime the user makes a move, the 2D Tile array that stores the current game board is
                   added to the move history and the score is added to the score history. If the user wants to undo
                   a move the program will remove the move from the move history, the score form the score history, set
                   the game board to the last 2D array stored in the move history and the score to the last integer in
                   the score history. This is an appropriate use of the concept because collections allow data to
                   easily be stored and removed.

  3. File IO - I used File IO to read the current game board and score to a file if the user presses a save game
               button. If the user wants to reload a saved game, they can press a load game button that will write
               the data saved to a file back into the game board. All previous moves and scores are written to the file
               so if a user reloads a game they can undo from the point they left off. This is an appropriate use of
               the concept because File IO allows data to be stored in a file and accessed at a later point.

  4. JUnit Testing - I used JUnit Testing to test different methods I wrote in my TwentyFortyEight class such as
                     the merge methods and the methods that check for the end game state (if the user won or loss).
                     This is an appropriate use of the concept because it allows verification of correct implementation
                     of a method without running the game itself (focusing on the model of the Model View Controller).

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
        Tile - creates a Tile class that makes up the 2D array that models the game board
        TwentyFortyEight - deals with the model of the Model View Controller by creating methods used to update the
                           game board and check for end game state
        RunTwentyFortyEight - deals with the view of the Model View Controller by adding all buttons and frames
                              to the game window
        GameBoard - deals with the controller of the Model View Controller by instantiating a TwentyFortyEight object
                    and updates the model when a user presses an arrow key
        Game - used to start and run the game

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
       The biggest challenge I encountered was combining the concepts of collections and File IO to allow the user
       to reload a saved game and be able to undo the previous moves of that game.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
        I think there is good separation of functionality in the way I organized my code. Each file focuses on
        one separate part of the Model View Controller which was a helpful method when thinking about what parts
        of the game I needed to implement. I first focused on creating the model of the game and then was able to
        think about how to incorporate the model into the view and controller.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
