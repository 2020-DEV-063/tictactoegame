package com.game.tictactoegame.service;

import com.game.tictactoegame.exceptions.OutsideOfBoardException;
import com.game.tictactoegame.exceptions.PositionOccupiedException;
import com.game.tictactoegame.pojo.Player;
import com.game.tictactoegame.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.game.tictactoegame.util.Constants.*;

/**
 * This class holds the game logic
 * @Author 2020-DEV-063
 */
@Setter
@Getter
@Component
public class TicTacToeGame {

    private final String[][] EMPTY_BOARD = {{" - ", " - ", " - "}, {" - ", " - ", " - "}, {" - ", " - ", " - "}};

    private String [][] board = EMPTY_BOARD;
    private StringBuilder boardLayout;

    private Player player1 = new Player(PLAYER1_NAME, PLAYER1_MARKER);
    private Player player2 = new Player(PLAYER2_NAME, PLAYER2_MARKER);
    private int turnCounter = ZERO_TURNS_PLAYED;
    private Player player;
    private boolean weHaveAWinner = false;

    private Scanner askForUserInput;

    /**
     * This method simulates a game
     */
    public void newGame() {
        while (turnCounter < MAX_NUMBER_OF_TURNS && !weHaveAWinner){
            printBoard();
            newTurn();
        }
        printBoard();
        System.out.println(printResult());
    }

    /**
     * This method simulates a turn
     */
    public void newTurn(){
        setPlayer(determineWhoIsAtTurn());
        int row;
        int column;
        boolean markerIsPut = false;
        while(!markerIsPut){
            try {
                System.out.println(player.getName() + ", on which row do you want to put " + player.getMarker() + "?");
                row = askPlayerInput();
                System.out.println("Okay, " + player.getName() + ". And in which column?");
                column = askPlayerInput();

                validateBoardPosition(row, column);
                updateBoard(row, column, getPlayer().getMarker());
                markerIsPut = true;
            } catch (OutsideOfBoardException | PositionOccupiedException exception) {
                System.out.println(exception.getMessage());
            }
        }
        turnCounter++;
    }

    /**
     * This method asks for input from the user and returns the input
     */
    public int askPlayerInput() {
        int input = 0;
        boolean askAgain = true;

        while (askAgain) {
            try {
                input = getAskForUserInput().nextInt();
                askAgain = false;
            } catch (InputMismatchException e) {
                System.out.println(INVALID_INPUT);
                getAskForUserInput().next();
            }
        }
        return input;
    }

    /**
     * Method takes row, column and marker of last move and uses them to check the different ways someone can win
     */
    public void checkAndAssignWinner(int row, int column, String marker){
        boolean winnerOnRow = checkWinnerOnRow(row-1);
        boolean winnerInColumn = checkWinnerInColumn(column-1);
        boolean winnerOnLeftUnderToRightTopDiagonal = checkWinnerOnLeftUnderToRightTopDiagonal(marker);
        boolean winnerOnLeftTopToRightUnderDiagonal = checkWinnerOnLeftTopToRightUnderDiagonal(marker);

        if(winnerOnRow || winnerInColumn || winnerOnLeftUnderToRightTopDiagonal || winnerOnLeftTopToRightUnderDiagonal){
            weHaveAWinner = true;
        }
    }

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
     * This method will return true if there is a winner on this diagonal
     */
    public boolean checkWinnerOnLeftUnderToRightTopDiagonal(String marker){
        return board[2][0].equals(marker) && board[1][1].equals(marker) && board[0][2].equals(marker);
    }

    /**
     * This method will return true if there is a winner on this diagonal
     */
    public boolean checkWinnerOnLeftTopToRightUnderDiagonal(String marker){
        return board[0][0].equals(marker) && board[1][1].equals(marker) && board[2][2].equals(marker);
    }

    /**
     * This method takes row, column and marker as arguments
     * and will put the given marker on the given position of the board
     * If the position is already occupied a PositionOccupiedException will be thrown.
     */
    public void updateBoard(int row, int column, String marker) throws PositionOccupiedException{
        if (getBoard()[row - 1][column - 1].equals(Constants.EMPTY_POSITION)) {
            getBoard()[row - 1][column - 1] = marker;
            checkAndAssignWinner(row, column, marker);
        } else
            throw new PositionOccupiedException();
    }

    /**
     * If the board position entered by player is not in the range of 1 to 3
     * OutsideOfBoardException will be thrown.
     */
    public void validateBoardPosition(int row, int column) throws OutsideOfBoardException{
        if ((row < 1 || row > 3) || (column < 1 || column > 3)) {
            throw new OutsideOfBoardException();
        }
    }

    /**
     * This method uses the turnCounter to determine who's turn it is
     */
    public Player determineWhoIsAtTurn(){
        if (getTurnCounter() % 2 == 0) {
            return getPlayer1();
        }
        return getPlayer2();
    }

    /**
     * This method will update the boardLayout and print it to the console
     */
    public void printBoard(){
        this.boardLayout = new StringBuilder();
        for(int row =0; row < 3; row ++) {
            for(int column=0; column < 3 ; column ++ ) {
                boardLayout.append(LAYOUT_SEPARATOR).append(getBoard()[row][column]);
            }
            boardLayout.append(LAYOUT_SEPARATOR).append(NEW_LINE);
        }
        System.out.println(boardLayout.toString());
    }

    /**
     * This method returns the correct string depending on which player is assigned to winner
     */
    public String printResult(){
        if (!isWeHaveAWinner()) {
            return DRAW;
        } else {
            if (player == player1) {
                return PLAYER1_WINS;
            } else return PLAYER2_WINS;
        }
    }

    public Scanner getAskForUserInput() {
        if(this.askForUserInput == null){
            this.askForUserInput = new Scanner(System.in);
        }
        return this.askForUserInput;
    }

}
