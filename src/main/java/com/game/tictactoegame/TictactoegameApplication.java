package com.game.tictactoegame;

import com.game.tictactoegame.service.TicTacToeGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TictactoegameApplication {

    public static void main(String[] args) {
        SpringApplication.run(TictactoegameApplication.class, args);

        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.printBoard();
        ticTacToeGame.updateBoard(1, 1, ticTacToeGame.getPlayer1().getMarker());
        ticTacToeGame.printBoard();
    }

}
