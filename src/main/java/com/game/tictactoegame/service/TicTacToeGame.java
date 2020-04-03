package com.game.tictactoegame.service;

import static com.game.tictactoegame.util.Constants.EMPTY_BOARD;

/**
 * This class holds the game logic
 * @Author 2020-DEV-063
 */
public class TicTacToeGame {

    private String [][] board = EMPTY_BOARD;

    public String[][] getBoard() {
        return board;
    }

}
