package com.ttt.Model;

import com.ttt.Entities.Game;
import com.ttt.Exceptions.GameCreationException;
import com.ttt.Exceptions.IllegalMoveException;
import com.ttt.Exceptions.PlayerAlreadyMovedException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agubba on 10/12/16.
 */
@Repository
public class GameDB {

    private static Map<String, Game> games; //game for each channel
    private static HashMap<String, Integer> moves; //all possible moves
    private static Map<String, int[]> winMaps;
    //[row1, row2, row3, col1, col2, col3, diag1, diag2]
    //have an array which on each operation increments the count for a given row or col or diag
    //this way, we can easily and incrementally check if someone has won and the game ends

    static {
        games = new HashMap<>();
        winMaps = new HashMap<>();
        moves = new HashMap<>();
        moves.put("Upper-Left", 0);
        moves.put("Upper-Center", 1);
        moves.put("Upper-Right", 2);
        moves.put("Middle-Left", 3);
        moves.put("Middle-Center", 4);
        moves.put("Middle-Right", 5);
        moves.put("Lower-Left", 6);
        moves.put("Lower-Center", 7);
        moves.put("Lower-Right", 8);
    }

    public boolean gameExists(String channel) {
        return games.containsKey(channel);
    }

    public Game getGame(String channel) {
        return games.get(channel);
    }

    public String getStateOfGames() {
        String ret = "";
        for (String k : games.keySet()) {
            ret += " | Key: " + k + " Players: " + games.get(k).getPlayer1() + " " + games.get(k).getPlayer2();
        }
        return ret;
    }

    public int[] getScore(String channel) {
        return winMaps.get(channel);
    }

    public HashMap<String, Integer> getMoves() {
        return moves;
    }

    public void deleteGame(String c) {
        games.remove(c);
        winMaps.remove(c);
    }

    public void updateMove(String channel, int index, String player) throws PlayerAlreadyMovedException, IllegalMoveException {
        Game currentGame = games.get(channel);
        if (currentGame == null) {
            throw new IllegalMoveException("There is no game yet in this channel! You cannot make a move before a game exists.");
        } else if (currentGame.getLastPlayer() != null && currentGame.getLastPlayer().equals(player) && !currentGame.getPlayer1().equals(currentGame.getPlayer2())) {//same player makes the move
            throw new PlayerAlreadyMovedException("You already moved! Let the your opponent move.");
        } else if (!(currentGame.getPlayer1().equals(player) || currentGame.getPlayer2().equals(player))) {
            throw new IllegalMoveException("You are not authorized to make this move. You are not playing the game!");
        }
        StringBuilder curGame = currentGame.getCurrentGame();
        int[] scoreForGame = winMaps.get(channel); //score array for this channel game
        if (curGame.charAt(index) != 'S') {
            throw new IllegalMoveException("You can't move there! That spot is taken.");
        }
        currentGame.setLastPlayer(player);
        //use a counter to make moves since you are playing yourself:
        if (currentGame.getPlayer1().equals(currentGame.getPlayer2())) {
            if (currentGame.getCount() % 2 == 0) {
                curGame.setCharAt(index, 'X');
                scoreForGame[index/3] += 1;
                scoreForGame[index%3 + 3] += 1;
                if (index == 0 || index == 4 || index == 8) {
                    scoreForGame[6] += 1;
                }
                if (index == 2 || index == 4 || index == 6) {
                    scoreForGame[7] += 1;
                }
            } else {
                curGame.setCharAt(index, 'O');
                scoreForGame[index / 3] -= 1;
                scoreForGame[index % 3 + 3] -= 1;
                if (index == 0 || index == 4 || index == 8) {
                    scoreForGame[6] -= 1;
                }
                if (index == 2 || index == 4 || index == 6) {
                    scoreForGame[7] -= 1;
                }
            }
            currentGame.setCount(currentGame.getCount() + 1);
        } else {
            curGame.setCharAt(index, player.equals(currentGame.getPlayer1()) ? 'X' : 'O');
            if (player.equals(currentGame.getPlayer1())) {
                scoreForGame[index/3] += 1;
                scoreForGame[index%3 + 3] += 1;
                if (index == 0 || index == 4 || index == 8) {
                    scoreForGame[6] += 1;
                }
                if (index == 2 || index == 4 || index == 6) {
                    scoreForGame[7] += 1;
                }
            } else {
                scoreForGame[index / 3] -= 1;
                scoreForGame[index % 3 + 3] -= 1;
                if (index == 0 || index == 4 || index == 8) {
                    scoreForGame[6] -= 1;
                }
                if (index == 2 || index == 4 || index == 6) {
                    scoreForGame[7] -= 1;
                }
            }
        }
    }

    public void addGame(String channel, String player1, String player2) throws GameCreationException {
        //This check should exist as you can't play against yourself but for the sake of the demo, I commented it out:
//        if (player1.equals(player2)) {
//            throw new GameCreationException("You cannot play against yourself!");
//        } else
        if (gameExists(channel)) {
            throw new GameCreationException("A game already exists in this channel!");
        }
        Game newGame = new Game(player1, player2);
        games.put(channel, newGame);
        int[] init = {0,0,0,0,0,0,0,0};
        winMaps.put(channel, init);
    }

    public String getGameBoard(String channel) throws GameCreationException {
        if (!gameExists(channel)) {
            throw new GameCreationException("No game in this channel exists!");
        }
        return games.get(channel).getCurrentGame().toString();
    }

}
