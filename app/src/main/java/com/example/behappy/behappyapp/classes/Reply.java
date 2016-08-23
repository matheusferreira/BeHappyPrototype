package com.example.behappy.behappyapp.classes;

import java.util.List;

/**
 * Created by Matheus on 01/07/2016.
 */
public class Reply extends Comment {

    Comment parent;

    public Reply(int id, String username, int avatarResourceID, String avatarURL, String date, String comment, int rating, List<Reply> replies, Comment parent) {
        super(id, username, avatarResourceID, avatarURL, date, comment, rating);
        this.parent = parent;
    }
}
