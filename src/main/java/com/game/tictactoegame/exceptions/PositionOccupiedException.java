package com.game.tictactoegame.exceptions;

import static com.game.tictactoegame.util.Constants.POSITION_OCCUPIED_EXCEPTION_MSG;

/**
 * This class holds the PositionOccupiedException
 * @Author 2020-DEV-063
 */
public class PositionOccupiedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return POSITION_OCCUPIED_EXCEPTION_MSG;
    }

}
