package com.game.tictactoegame.test;

import com.game.tictactoegame.exceptions.PositionOccupiedException;
import com.game.tictactoegame.service.TicTacToeGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is to test the TicTacToeGame class
 * @Author 2020-DEV-063
 */
class TicTacToeGameTest {

    private TicTacToeGame ticTacToeGame;

    @BeforeEach
    void init() {
        ticTacToeGame = new TicTacToeGame();
    }

    /**
     * To check if there are 9 empty positions on the board
     */
    @Test
    void testNumberOfPositionsOnBoard() {
        String[][] testBoard = ticTacToeGame.getBoard();
        assertEquals(9, testBoard[0].length + testBoard[1].length + testBoard[2].length);
    }

    /**
     * To check if the boardLayout is empty or not
     */
    @Test
    void testBoardLayoutIsCreated(){
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
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
        assertEquals(ticTacToeGame.getPlayer1(), ticTacToeGame.getPlayerAtTurn());

        ticTacToeGame.setTurnCounter(5);
        ticTacToeGame.determineWhoIsAtTurn();
        assertEquals(ticTacToeGame.getPlayer2(), ticTacToeGame.getPlayerAtTurn());
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
     * To check if there is a winner on the given row
     */
    @Test
    void testCheckForWinnerOnRow(){
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 2, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.updateBoard(1, 3, ticTacToeGame.getPlayer1().getMarker());

        assertTrue(ticTacToeGame.checkWinnerOnRow(1));
    }
}