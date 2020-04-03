package com.game.tictactoegame;

import com.game.tictactoegame.service.TicTacToeGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TictactoegameApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TictactoegameApplication.class, args);

        TicTacToeGame ticTacToeGame = (TicTacToeGame) ctx.getBean("ticTacToeGame");
        ticTacToeGame.newGame();
    }

}
