package com.ttt.Entities;

public class TTTRequest {

    private final String token;
    private final String team_id;
    private final String channel_id;
    private final String channel_name;
    private final String user_id;
    private final String user_name;
    private final String command;
    private final String text;
    private final String response_url;

    public TTTRequest(String token, String team_id, String channel_id, String channel_name, String user_id, String user_name, String command, String text, String response_url) {
        this.token = token;
        this.team_id = team_id;
        this.channel_id = channel_id;
        this.channel_name = channel_name;
        this.user_id = user_id;
        this.user_name = user_name;
        this.command = command;
        this.text = text;
        this.response_url = response_url;
    }

    public String getToken() {
        return token;
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getCommand() {
        return command;
    }

    public String getText() {
        return text;
    }

    public String getResponse_url() {
        return response_url;
    }
}