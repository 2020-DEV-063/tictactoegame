package com.game.tictactoegame.exceptions;

/**
 * This class holds the PositionOccupiedException
 * @Author 2020-DEV-063
 */
public class PositionOccupiedException extends RuntimeException{
    private String message = "This position is already occupied. Please, choose another position to put you marker.";

    @Override
    public String getMessage() {
        return message;
    }
}
