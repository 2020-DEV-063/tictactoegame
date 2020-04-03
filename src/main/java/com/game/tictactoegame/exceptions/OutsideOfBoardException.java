package com.game.tictactoegame.exceptions;

import com.game.tictactoegame.util.Constants;

import static com.game.tictactoegame.util.Constants.OUTSIDEOFBOARD_EXCEPTION_MSG;

/**
 * This class holds the OutsideOfBoardException
 * @Author 2020-DEV-063
 */
public class OutsideOfBoardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return OUTSIDEOFBOARD_EXCEPTION_MSG;
    }
}
