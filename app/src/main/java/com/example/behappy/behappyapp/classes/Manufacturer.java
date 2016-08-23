package com.example.behappy.behappyapp.classes;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Matheus on 05/07/2016.
 */
public class Manufacturer {

    private int ID;
    private String name;
    private String site;
    private String contactInfo;

    ArrayList<Group> groupRestriction = new ArrayList<Group>();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ArrayList<News> news = new ArrayList<News>();
    ArrayList<Product> productList = new ArrayList<Product>();

    public Manufacturer(int ID, String name, String site, String contactInfo) {
        this.ID = ID;
        this.name = name;
        this.site = site;
        this.contactInfo = contactInfo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ArrayList<Group> getGroupRestriction() {
        return groupRestriction;
    }

    public void setGroupRestriction(ArrayList<Group> groupRestriction) {
        this.groupRestriction = groupRestriction;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
}
