package com.ttt.Entities;

import java.util.HashMap;

/**
 * Created by agubba on 10/12/16.
 */

public class TTTInResponse {

    private HashMap<String, String>[] attachments;

    public void setAttachments(HashMap<String, String>[] attachments) {
        this.attachments = attachments;
    }

    public TTTInResponse() {
        this.attachments = new HashMap[1];
        attachments[0] = new HashMap<>();
    }

    public HashMap<String, String>[] getAttachments() {
        return attachments;
    }

    public void setTitle(String title) {
        this.attachments[0].put("title", title);
    }

    public void setText(String text) {
        this.attachments[0].put("text", text);
    }

    public void setImageUrl(String image_url) {
        this.attachments[0].put("image_url", image_url);
    }

    public void setColor(String color) {
        this.attachments[0].put("color", color);
    }



}
