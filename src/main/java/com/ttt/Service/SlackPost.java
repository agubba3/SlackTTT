package com.ttt.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by agubba on 10/19/16.
 */
public class SlackPost {

    // HTTP POST request

    /**
     * Post request to send a post to the channel so everyone can see
     * the game after a move has been made and who's turn it is.
     * The slash command is only able to send a response to the sender
     * This posts to the entire channel.
     * @param ChannelName
     * @param text
     * @throws Exception
     */
    public static void sendPostM(String ChannelName, String text) throws Exception {

        URL obj = new URL("https://slack.com/api/chat.postMessage");
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //I have stored the token privately but refrained from pushing publically to github
        //The deployed version has the proper token so POST requests can occur.
        String urlParameters  = "token=";
        urlParameters += "&channel=" + ChannelName;
        urlParameters += "&text=" + text;
        urlParameters += "&username=" + "TTT Bot";
        urlParameters += "&icon_url=" + "http://jpmec.com/wp-includes/js/jpmec.tictactoe/board.png";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
