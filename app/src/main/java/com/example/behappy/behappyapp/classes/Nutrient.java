package com.example.behappy.behappyapp.classes;

/**
 * Created by Matheus on 05/07/2016.
 */
public class Nutrient {

    private int id;
    private String name;
    private String quantityType;
    private int quantityNumber;
    private int referenceValue;
    private int average;
    private int rating;

    public Nutrient(int id, String name, String quantityType, int quantityNumber, int rating) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.quantityType = quantityType;
        this.quantityNumber = quantityNumber;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public int getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(int quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public int getReferenceValue() {
        return referenceValue;
    }

    public void setReferenceValue(int referenceValue) {
        this.referenceValue = referenceValue;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
