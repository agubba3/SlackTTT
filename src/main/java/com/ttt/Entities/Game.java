package com.ttt.Entities;

import com.ttt.ImgConfig;

import java.awt.image.BufferedImage;

/**
 * Created by agubba on 10/12/16.
 */
public class Game {
    /*
    | X | O | O |
    |---+---+---|
    | O | X | X |
    |---+---+---|
    | X | O | X |
     */
    private String player1;
    private String player2;
    private StringBuilder currentGame;
    private String lastPlayer;
    private int counter;
    private BufferedImage board;
    //represent the board as a string reading the board from left to right
    //example above would be XOOOXXXOX

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentGame = new StringBuilder("SSSSSSSSS");
        this.counter = 0;
        this.board = new ImgConfig().getBoard();
    }

    public BufferedImage getcurrentboard() {return board;}

    public String getPlayer1() {
        return player1;
    }

    public String getLastPlayer() {
        return lastPlayer;
    }

    public int getCount() {
        return this.counter;
    }

    public void setCount(int c) { this.counter =  c; }

    public void setLastPlayer(String p) {
        this.lastPlayer = p;
    }

    public String getPlayer2() {
        return player2;
    }

    public StringBuilder getCurrentGame() {
        return currentGame;
    }

}
