package com.example.behappy.behappyapp.classes;

import java.util.List;

/**
 * Created by Matheus on 01/07/2016.
 */
public class Comment {

    int id;
    String username;
    int avatarResourceID; //TODO versao de produção, bitmap.
    String avatarURL;
    String date;
    String comment;
    int rating;
    private List<Reply> replies;


    public Comment(int id, String username, int avatarResourceID, String avatarURL, String date, String comment, int rating) {
        this.id = id;
        this.username = username;
        this.avatarResourceID = avatarResourceID;
        this.date = date;
        this.comment = comment;
        this.rating = rating;
        this.avatarURL = avatarURL;

    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAvatarResourceID() {
        return avatarResourceID;
    }

    public void setAvatarResourceID(int avatarResourceID) {
        this.avatarResourceID = avatarResourceID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
