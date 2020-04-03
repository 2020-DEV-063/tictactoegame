package com.game.tictactoegame.exceptions;

/**
 * This class holds the OutsideOfBoardException
 * @Author 2020-DEV-063
 */
public class OutsideOfBoardException extends RuntimeException {
    private String message = "This position is outside of the board. Please, choose another position to put you marker.";

    @Override
    public String getMessage() {
        return message;
    }
}
