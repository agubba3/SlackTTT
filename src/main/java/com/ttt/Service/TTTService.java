package com.ttt.Service;

import com.ttt.Exceptions.GameCreationException;
import com.ttt.Exceptions.IllegalMoveException;
import com.ttt.Exceptions.PlayerAlreadyMovedException;
import com.ttt.ImgConfig;
import com.ttt.Model.GameDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by agubba on 10/12/16.
 */
@Service
public class TTTService {

    @Autowired
    private GameDB gamedb; //only want one instance of the db which can only talk to this service

    /**
     * Add a game with two new players and only one can exist per channel
     * Error handling happens after getting data from the model.
     * @param Channel
     * @param player1
     * @param player2
     * @throws GameCreationException
     */
    public void addGame(String Channel, String player1, String player2) throws GameCreationException {
        gamedb.addGame(Channel, player1, player2);
    }

    /**
     * Given a channel, update the move for the game within the channel.
     * Error handling happens after getting data from the model.
     * @param Channel
     * @param index
     * @param player
     * @throws PlayerAlreadyMovedException
     * @throws IllegalMoveException
     */
    public void addMove(String Channel, int index, String player) throws PlayerAlreadyMovedException, IllegalMoveException {
        gamedb.updateMove(Channel, index, player);
    }

    /**
     * Algorithm to determine if a game is over.
     * This is quite efficient since we are using the score arrays and incrementally adding information
     * to know if the game is over
     * @param Channel
     * @return
     */
    public String whoWon(String Channel) {
        String gameState = gamedb.getGame(Channel).getCurrentGame().toString();
        int[] scoreArr = gamedb.getScore(Channel);
        for (int i = 0; i < scoreArr.length; i++) {
            if (scoreArr[i] == 3) {
                return "X - " + gamedb.getGame(Channel).getPlayer1() + " wins!";
            } else if (scoreArr[i] == -3) {
                return "O - " + gamedb.getGame(Channel).getPlayer2() + " wins!";
            }
        }
        for (int i = 0; i < gameState.length(); i++) {
            if (gameState.charAt(i) == 'S') {
                return null;
            } else if (i == gameState.length() - 1) {
                return "Stalemate";
            }
        }
        return null;
    }

    /**
     * After winning, delete the channel from the db
     * @param Channel
     */
    public void cleanup(String Channel) {
        gamedb.deleteGame(Channel);
    }

    public String getGameBoard(String Channel) throws GameCreationException {
        return gamedb.getGameBoard(Channel);
    }

    public String getAllChannels() {
        return gamedb.getStateOfGames();
    }

    /**
     * Get the current player who must move next.
     * @param Channel
     * @return
     */
    public String getCurrentPlayerTurn(String Channel) {
        if (gamedb.getGame(Channel) == null) {
            return "No game exists in this channel. Perhaps you should start one!";
        }
        String last = gamedb.getGame(Channel).getLastPlayer();
        if (last == null) {
            return "None of the players have moved for this game yet!";
        }
        String nextToPlayUserName = last.equals(gamedb.getGame(Channel).getPlayer1())
                ?  gamedb.getGame(Channel).getPlayer2()
                : gamedb.getGame(Channel).getPlayer1();
        return "It is " + nextToPlayUserName + "'s turn";
    }

    public boolean isValidMove(String move) {
        return gamedb.getMoves().keySet().contains(move);
    }

    public int getIndexOfMove(String move) {
        return gamedb.getMoves().get(move);
    }

    /**
     * Get image methods which gets the current image from the channel and renders it
     * @param Channel
     * @return
     * @throws GameCreationException
     */
    public BufferedImage getImgByChannel(String Channel) throws GameCreationException {
        String curGame = getGameBoard(Channel);
        ImgConfig ic = new ImgConfig();
        BufferedImage imageO = ic.getO();
        BufferedImage imageX = ic.getX();
        BufferedImage board = gamedb.getGame(Channel).getcurrentboard();
        Graphics g = board.getGraphics();
        for (int i = 0; i < curGame.length(); i++) {
            int width = (((i % 3) * 165) + 30);
            int height = (((i / 3) * 165) + 30);
            //checking for valid moves already occurs
            if (curGame.charAt(i) == 'X') {
                g.drawImage(imageX, width, height, null);
            } else if (curGame.charAt(i) == 'O') {
                g.drawImage(imageO, width, height, null);
            }
        }
        return board;
    }

    public BufferedImage getImgByState(String State) throws GameCreationException {
        String curGame = State;
        ImgConfig ic = new ImgConfig();
        BufferedImage imageO = ic.getO();
        BufferedImage imageX = ic.getX();
        BufferedImage board = ic.getBoard();
        Graphics g = board.getGraphics();
        for (int i = 0; i < curGame.length(); i++) {
            int width = (((i % 3) * 165) + 30);
            int height = (((i / 3) * 165) + 30);
            //checking for valid moves already occurs
            if (curGame.charAt(i) == 'X') {
                g.drawImage(imageX, width, height, null);
            } else if (curGame.charAt(i) == 'O') {
                g.drawImage(imageO, width, height, null);
            }
        }
        return board;
    }

}

