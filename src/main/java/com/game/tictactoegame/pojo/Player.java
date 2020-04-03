package com.game.tictactoegame.pojo;

/**
 * This class holds the Player pojo
 * @Author 2020-DEV-063
 */
public class Player {
    private String name;
    private String marker;

    public Player(String name, String marker) {
        this.name = name;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public String getMarker() {
        return marker;
    }
}
