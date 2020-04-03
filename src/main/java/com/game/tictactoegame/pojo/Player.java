package com.game.tictactoegame.pojo;

import lombok.Getter;

/**
 * This class holds the Player pojo
 * @Author 2020-DEV-063
 */
@Getter
public class Player {
    private String name;
    private String marker;

    public Player(String name, String marker) {
        this.name = name;
        this.marker = marker;
    }

}
