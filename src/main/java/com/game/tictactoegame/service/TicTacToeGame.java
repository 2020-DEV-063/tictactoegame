package com.game.tictactoegame.service;

import com.game.tictactoegame.exceptions.PositionOccupiedException;
import com.game.tictactoegame.pojo.Player;

import static com.game.tictactoegame.util.Constants.*;

/**
 * This class holds the game logic
 * @Author 2020-DEV-063
 */
public class TicTacToeGame {

    public final String[][] EMPTY_BOARD = {{" - ", " - ", " - "}, {" - ", " - ", " - "}, {" - ", " - ", " - "}};

    private String [][] board = EMPTY_BOARD;
    private StringBuilder boardLayout;

    private Player player1 = new Player(PLAYER1_NAME, PLAYER1_MARKER);
    private Player player2 = new Player(PLAYER2_NAME, PLAYER2_MARKER);
    private int turnCounter = ZERO_TURNS_PLAYED;
    private Player playerAtTurn;

    /**
     * This method will return true if there is a winner on the given row
     */
    public boolean checkWinnerOnRow(int row) {
        String firstColumn = board[row][0];
        for (int column = 1; column < board[row].length; column++) {
            if (!board[row][column].equals(firstColumn))
                return false;
        }
        return true;
    }

    /**
     * This method will return true if there is a winner in the given column
     */
    public boolean checkWinnerInColumn(int column) {
        String firstRow = board[0][column];
        for (int row = 1; row < board.length; row++){
            if (!board[row][column].equals(firstRow))
                return false;
        }
        return true;
    }

    /**
     * This method takes row, column and marker as arguments
     * and will put the given marker on the given position of the board
     * If the position is already occupied a PositionOccupiedException will be thrown
     */
    public void updateBoard(int row, int column, String marker) throws PositionOccupiedException {
        if (board[row - 1][column - 1].equals(EMPTY_POSITION)) {
            board[row - 1][column - 1] = marker;
        } else throw new PositionOccupiedException();
    }

    /**
     * This method uses the turnCounter to determine who's turn it is
     */
    public void determineWhoIsAtTurn(){
        if(turnCounter % 2 == 0){
            playerAtTurn = player1;
        } else {
            playerAtTurn = player2;
        }
    }

    /**
     * This method will update the boardLayout and print it to the console
     */
    public void printBoard(){
        boardLayout = new StringBuilder();
        for(int row =0; row < 3; row ++) {
            for(int column=0; column < 3 ; column ++ ) {
                boardLayout.append(LAYOUT_SEPARATOR).append(board[row][column]);
            }
            boardLayout.append(LAYOUT_SEPARATOR).append(NEW_LINE);
        }
        System.out.println(boardLayout.toString());
    }

    public String[][] getBoard() {
        return board;
    }

    public StringBuilder getBoardLayout() {
        return boardLayout;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayerAtTurn() {
        return playerAtTurn;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

}
