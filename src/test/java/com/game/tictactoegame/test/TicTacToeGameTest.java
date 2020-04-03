package com.game.tictactoegame.test;

import static com.game.tictactoegame.util.Constants.DRAW;
import static com.game.tictactoegame.util.Constants.PLAYER1_MARKER;
import static com.game.tictactoegame.util.Constants.PLAYER1_WINS;
import static com.game.tictactoegame.util.Constants.PLAYER2_WINS;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.game.tictactoegame.exceptions.OutsideOfBoardException;
import com.game.tictactoegame.exceptions.PositionOccupiedException;
import com.game.tictactoegame.service.TicTacToeGame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is to test the TicTacToeGame class
 * @Author 2020-DEV-063
 */
class TicTacToeGameTest{

    private TicTacToeGame ticTacToeGame;

    @BeforeEach
    void init(){
        ticTacToeGame = new TicTacToeGame();
    }

    /**
     * To check if there are 9 empty positions on the board
     */
    @Test
    void testNumberOfPositionsOnBoard(){
        String[][] testBoard = ticTacToeGame.getBoard();
        assertEquals(9, testBoard[0].length + testBoard[1].length + testBoard[2].length);
    }

    /**
     * To check if the boardLayout is empty or not
     */
    @Test
    void testBoardLayoutIsCreated(){
        ticTacToeGame.printBoard();

        assertFalse(ticTacToeGame.getBoardLayout().toString().isEmpty());
    }

    /**
     * To check which players turn it is
     * The TicTacToeGame class will contain a turnCounter variable that will be incremented every turn
     * player1 is at turn when the turnCounter is even. When the turnCounter is uneven, player2 is at turn
     */
    @Test
    void testWhoIsAtTurn(){
        ticTacToeGame.setTurnCounter(4);
        ticTacToeGame.determineWhoIsAtTurn();
        assertEquals(ticTacToeGame.getPlayer1(), ticTacToeGame.getPlayer());

        ticTacToeGame.setTurnCounter(5);
        ticTacToeGame.determineWhoIsAtTurn();
        assertEquals(ticTacToeGame.getPlayer2(), ticTacToeGame.getPlayer());
    }

    /**
     * To check if the updateBoard method changes the value of the given position correctly
     */
    @Test
    void testUpdateBoard(){
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(2, 3, ticTacToeGame.getPlayer2().getMarker());
        String[][] testBoard = ticTacToeGame.getBoard();

        assertEquals(ticTacToeGame.getPlayer1().getMarker(), testBoard[0][0]);
        assertEquals(ticTacToeGame.getPlayer2().getMarker(), testBoard[1][2]);
    }

    /**
     * To check if the updateBoard method throws an exception if a player tries to put his marker
     * on an already occupied position on the board
     */
    @Test
    void testUpdateBoardThrowsPositionOccupiedException(){
        ticTacToeGame.updateBoard(2, 2, ticTacToeGame.getPlayer1().getMarker());

        assertThrows(PositionOccupiedException.class,
                () -> ticTacToeGame.updateBoard(2, 2, ticTacToeGame.getPlayer2().getMarker()));
    }

    /**
     * To check if the updateBoard method throws an exception if a player tries to put his marker
     * outside the range of the board
     */
    @Test
    void testUpdateBoardThrowsOutsideOfBoardException(){
        assertThrows(OutsideOfBoardException.class,
                () -> ticTacToeGame.updateBoard(4, 2, ticTacToeGame.getPlayer1().getMarker()));
    }

    /**
     * To check if there is a winner on the given row
     */
    @Test
    void testCheckForWinnerOnRow(){
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 2, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 3, ticTacToeGame.getPlayer1().getMarker());

        assertTrue(ticTacToeGame.checkWinnerOnRow(1));
    }

    /**
     * To check if there is a winner in the given column
     */
    @Test
    void testCheckForWinnerInColumn(){
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(2, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(3, 1, ticTacToeGame.getPlayer1().getMarker());

        assertTrue(ticTacToeGame.checkWinnerInColumn(1));
    }

    /**
     * Test to check if there is a winner on diagonal from left under to right top
     */
    @Test
    void testCheckForWinnerOnLeftUnderToRightTopDiagonal(){
        ticTacToeGame.updateBoard(3, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(2, 2, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 3, ticTacToeGame.getPlayer1().getMarker());

        assertTrue(ticTacToeGame.checkWinnerOnLeftUnderToRightTopDiagonal(ticTacToeGame.getPlayer1().getMarker()));
    }

    /**
     * Test to check if there is a winner on diagonal from left top to right under
     */
    @Test
    void testCheckForWinnerOnLeftTopToRightUnderDiagonal(){
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(2, 2, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(3, 3, ticTacToeGame.getPlayer1().getMarker());

        assertTrue(ticTacToeGame.checkWinnerOnLeftTopToRightUnderDiagonal(ticTacToeGame.getPlayer1().getMarker()));
    }

    /**
     * Test to check if the right player is assigned as the winner
     * when one of the different methods to check for a winner returns true
     * The playerAtTurn should be assigned to winner
     */
    @Test
    void testAssignWinner(){
        ticTacToeGame.setPlayer(ticTacToeGame.getPlayer1());
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 2, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 3, ticTacToeGame.getPlayer1().getMarker());

        assertEquals(ticTacToeGame.getPlayer1(), ticTacToeGame.getPlayer());
    }

    /**
     * To check if printResult method return the right constant string
     */
    @Test
    void testPrintResult(){
        assertEquals(DRAW, ticTacToeGame.printResult());

        ticTacToeGame.setPlayer(ticTacToeGame.getPlayer1());
        ticTacToeGame.setWeHaveAWinner(true);
        assertEquals(PLAYER1_WINS, ticTacToeGame.printResult());

        ticTacToeGame.setPlayer(ticTacToeGame.getPlayer2());
        ticTacToeGame.setWeHaveAWinner(true);
        assertEquals(PLAYER2_WINS, ticTacToeGame.printResult());
    }

    /**
     * To check if the askPlayerInput method returns the input of the user
     */
    @Test
    void testAskPlayerInput(){
        Scanner mockScanner = mock(Scanner.class);
        ticTacToeGame.setAskForUserInput(mockScanner);

        when(ticTacToeGame.getAskForUserInput().nextInt()).thenReturn(1);

        assertEquals(1, ticTacToeGame.askPlayerInput());
    }

    /**
     * To check if the InputMismatchException is caught inside the askPlayerInput method
     */
    @Test
    void testInvalidInput(){
        Scanner mockScanner = mock(Scanner.class);
        ticTacToeGame.setAskForUserInput(mockScanner);

        when(ticTacToeGame.getAskForUserInput().nextInt()).thenThrow(new InputMismatchException()).thenReturn(1);
        assertDoesNotThrow(() -> ticTacToeGame.askPlayerInput());
    }

    /**
     * To check if the newTurn method uses the user input
     * to put the players marker on the correct position on the board
     */
    @Test
    void testNewTurn(){
        Scanner mockScanner = mock(Scanner.class);
        ticTacToeGame.setAskForUserInput(mockScanner);

        when(ticTacToeGame.getAskForUserInput().nextInt()).thenReturn(1).thenReturn(2);

        ticTacToeGame.newTurn();
        assertEquals(PLAYER1_MARKER, ticTacToeGame.getBoard()[0][1]);
    }

    /**
     * To check if the PositionOccupiedException is caught inside the newTurn method
     */
    @Test
    void testNewTurnPositionOccupiedException(){
        Scanner mockScanner = mock(Scanner.class);
        ticTacToeGame.setAskForUserInput(mockScanner);

        when(ticTacToeGame.getAskForUserInput().nextInt()).thenReturn(1).thenThrow(PositionOccupiedException.class)
                .thenReturn(1).thenReturn(1);

        assertDoesNotThrow(() -> ticTacToeGame.newTurn());
    }

    /**
     * To check if the OutsideOfBoardException is caught inside the newTurn method
     */
    @Test
    void testNewTurnOutsideOfBoardException(){
        Scanner mockScanner = mock(Scanner.class);
        ticTacToeGame.setAskForUserInput(mockScanner);

        when(ticTacToeGame.getAskForUserInput().nextInt()).thenThrow(OutsideOfBoardException.class).thenReturn(1);

        assertDoesNotThrow(() -> ticTacToeGame.newTurn());
    }

    /**
     * Test for the newGame method with scenario where Player 1 wins
     */
    @Test
    void testNewGamePlayer1Wins(){
        Scanner mockScanner = mock(Scanner.class);
        ticTacToeGame.setAskForUserInput(mockScanner);

        when(ticTacToeGame.getAskForUserInput().nextInt()).thenReturn(1).thenReturn(1)
                .thenReturn(1).thenReturn(2)
                .thenReturn(2).thenReturn(2)
                .thenReturn(1).thenReturn(3)
                .thenReturn(3).thenReturn(3);

        ticTacToeGame.newGame();
        assertTrue(ticTacToeGame.isWeHaveAWinner());
        assertEquals(ticTacToeGame.getPlayer1(), ticTacToeGame.getPlayer());
    }
}