package com.example.behappy.behappyapp.classes;

/**
 * Created by Matheus on 05/07/2016.
 */
public class Group {

    private String name;
    private String description;
    private String classification; //Seguro, Recomendavel, Não-Classificado, Perigoso, Não Recomendado.

    public Group(String name, String classification) {
        this.name = name;
        this.classification = classification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
