package com.ttt.Controller;

import com.ttt.Entities.TTTInResponse;
import com.ttt.Exceptions.GameCreationException;
import com.ttt.Exceptions.IllegalMoveException;
import com.ttt.Exceptions.InvalidCommandException;
import com.ttt.Exceptions.PlayerAlreadyMovedException;
import com.ttt.Service.TTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tttIn")
public class TTTController {

    @Autowired
    private TTTService tttService; //dependency inject this service into the controller to reduce coupling in our system
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public TTTInResponse inputGame(@RequestParam(value="token", defaultValue="") String token
    ,@RequestParam(value="team_id", defaultValue="") String team_id
    ,@RequestParam(value="channel_id", defaultValue="") String channel_id
    ,@RequestParam(value="channel_name", defaultValue="") String channel_name
    ,@RequestParam(value="user_id", defaultValue="") String user_id
    ,@RequestParam(value="user_name", defaultValue="") String user_name
    ,@RequestParam(value="command", defaultValue="") String command
    ,@RequestParam(value="text", defaultValue="") String text
    ,@RequestParam(value="response_url", defaultValue="") String response_url) {

        TTTInResponse resp = new TTTInResponse();

        if (!token.equals("EfJXnscjLhFos1nEvYsz6LHM") || channel_name.equals("") || channel_id.equals("") || user_name.equals("") || !command.equals("/ttt") || text.equals("")) {
            resp.setColor("#f90000");
            resp.setTitle("Auth/Get Problem");
            resp.setText("Not all information is present to process your request!");
            return resp;
        }

        //since this is the only endpoint, analyze the incoming text (command)
        try {
            //can either be a username, "status", or next move position
            String commandText = cleanParams(text);
            //get host name of the server so it can call itself as an image endpoint
            String localhostname = "http://gsrestservice-agubba3.boxfuse.io";
            //assuming the command is valid, we proceed
            if (commandText.equals("status")) {
                if (tttService.getCurrentPlayerTurn(channel_id).equals("No game exists in this channel. Perhaps you should start one!")
                        || tttService.getCurrentPlayerTurn(channel_id).equals("None of the players have moved for this game yet!")) {
                    resp.setTitle("Nothing to Show");
                    resp.setText(tttService.getCurrentPlayerTurn(channel_id));
                    resp.setColor("#f90000");
                } else {
                    resp.setTitle("Current Status of the Game in Channel " + channel_name);
                    resp.setImageUrl(localhostname + ":8080/imageOut?channel_id=" + channel_id + "&count=" + counter.incrementAndGet());
                    resp.setText(tttService.getCurrentPlayerTurn(channel_id));
                    resp.setColor("#00f93a");
                }
            } else if (tttService.isValidMove(commandText)) {
                //make a move to an existing game and also check if the game is done
                tttService.addMove(channel_id, tttService.getIndexOfMove(commandText), user_name);
                if (tttService.whoWon(channel_id) == null) {
                    resp.setTitle(user_name + "'s Move");
                    resp.setImageUrl(localhostname + ":8080/imageOut?channel_id=" + channel_id + "&count=" + counter.incrementAndGet());
                    resp.setText("This is what the new status of the board is after " + user_name + " moved.");
                    resp.setColor("#00f93a");
                } else { //game is over
                    resp.setTitle("Game Over! Final Board.");
                    String state = tttService.getGameBoard(channel_id);
                    resp.setImageUrl(localhostname + ":8080/imageOut?state=" + state + "&count=" + counter.incrementAndGet());
                    resp.setText(tttService.whoWon(channel_id));
                    resp.setColor("#00f93a");
                    tttService.cleanup(channel_id);
                }
            } else if (commandText.charAt(0) == '@') {
                //add the game
                tttService.addGame(channel_id, user_name, clean(commandText));
                resp.setTitle("New Game!");
                resp.setImageUrl(localhostname + ":8080/imageOut?channel_id=" + channel_id + "&count=" + counter.incrementAndGet());
                resp.setText("Game Added in channel: " + channel_name + " between " + clean(user_name) + " and " + clean(commandText));
                resp.setColor("#00f93a");
            } else {
                resp.setColor("#f90000");
                resp.setTitle("Command Problem");
                resp.setText("Invalid username or move location! Please refer to the 3 commands. You must have an @ before the opponent's username.");
            }
            return resp;
        } catch (InvalidCommandException e) {
            resp.setColor("#f90000");
            resp.setTitle("Command Problem");
            resp.setText(e.getMessage());
            return resp;
        } catch (GameCreationException e) {
            resp.setColor("#f90000");
            resp.setTitle("Game Creation/Logic Problem");
            resp.setText(e.getMessage());
            return resp;
        } catch (PlayerAlreadyMovedException e) {
            resp.setColor("#f90000");
            resp.setTitle("Player Turn Problem");
            resp.setText(e.getMessage());
            return resp;
        } catch (IllegalMoveException e) {
            resp.setColor("#f90000");
            resp.setTitle("Illegal Move Problem");
            resp.setText(e.getMessage());
            return resp;
        }
    }

    public String clean(String name) {
        name = name.trim();
        name = name.toLowerCase();
        StringBuilder nBuilder = new StringBuilder(name);
        if (nBuilder.charAt(0) == '@') {
            nBuilder.deleteCharAt(0);
        }
        return nBuilder.toString();
    }

    public String cleanParams(String text) throws InvalidCommandException {
        ArrayList<String> params = new ArrayList<>(Arrays.asList(text.split(" "))); //split command by space
        ArrayList<String> paramsClean = new ArrayList<>(params);
        int rc = 0;
        for (int i = 0; i < params.size();i ++) {
            if (params.get(i).equals(" ") || params.get(i).equals("")) {
                paramsClean.remove(i - rc);
                rc++;
            }
        }
        if (paramsClean.size() > 1) { //should only be player name or status
            throw new InvalidCommandException("Your command is invalid! Please refer to the 3 commands used to play the game.");
        }
        return paramsClean.get(0);
    }
}