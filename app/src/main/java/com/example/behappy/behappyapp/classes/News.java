package com.example.behappy.behappyapp.classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matheus on 30/06/2016.
 */
public class News implements Serializable {

    private String title;
    private String subtitle;
    private String data;
    private String source;
    private String url;
    private String author;
    private String body; //precisa ver qual será a forma mais adequada de armazenar os textos.
    private int imgResourceID;
    private int rating;

    public News(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getImgResourceID() {
        return imgResourceID;
    }

    public void setImgResourceID(int imgResourceID) {
        this.imgResourceID = imgResourceID;
    }

    public String getFont() {
        return source;
    }

    public void setFont(String font) {
        this.source = font;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
