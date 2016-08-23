package com.example.behappy.behappyapp.classes;

import java.util.ArrayList;

/**
 * Created by Matheus on 05/07/2016.
 */
public class Product {

    private int ID;
    private String name;
    private String imgResource;
    private String category;
    private String countryOrigin;
    private Manufacturer manufacturer;
    private int rating;
    private String curiosity;
    private String portionType;
    private int portionValue;


    ArrayList<Group> groups = new ArrayList<Group>();
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>();
    ArrayList<String> certifications = new ArrayList<String>();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ArrayList<News> news = new ArrayList<News>();
    ArrayList<Product> similarProducts = new ArrayList<Product>();

    public Product(int ID, String name, String imgResourceID, String countryOrigin, Manufacturer manufacturer, int rating, String curiosity) {
        this.ID = ID;
        this.name = name;
        this.imgResource = imgResourceID;
        this.countryOrigin = countryOrigin;
        this.manufacturer = manufacturer;
        this.rating = rating;
        this.curiosity = curiosity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPortionType() {
        return portionType;
    }

    public void setPortionType(String portionType) {
        this.portionType = portionType;
    }

    public int getPortionValue() {
        return portionValue;
    }

    public void setPortionValue(int portionValue) {
        this.portionValue = portionValue;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImgResourceID() {
        return imgResource;
    }

    public void setImgResourceID(String imgResourceID) {
        this.imgResource = imgResourceID;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCuriosity() {
        return curiosity;
    }

    public void setCuriosity(String curiosity) {
        this.curiosity = curiosity;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(ArrayList<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public ArrayList<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(ArrayList<String> certifications) {
        this.certifications = certifications;
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

    public ArrayList<Product> getSimilarProducts() {
        return similarProducts;
    }

    public void setSimilarProducts(ArrayList<Product> similarProducts) {
        this.similarProducts = similarProducts;
    }
}
