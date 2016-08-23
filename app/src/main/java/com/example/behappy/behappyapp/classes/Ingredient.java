package com.example.behappy.behappyapp.classes;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Matheus on 05/07/2016.
 */
public class Ingredient extends Nutrient {

    private String description;
    private String category;
    private String processType;
    private URL wikiSite;

    ArrayList<String> synonyms = new ArrayList<String>();
    ArrayList<Group> groups = new ArrayList<Group>();
    ArrayList<String> certifications = new ArrayList<String>();
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ArrayList<Comment> specialistOpinions = new ArrayList<Comment>();
    ArrayList<News> news = new ArrayList<News>();
    ArrayList<News> scientificStudies = new ArrayList<News>();

    public Ingredient(int id, String name, String quantityType, int quantityNumber, int rating) {
        super(id, name, quantityType, quantityNumber, rating);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public URL getWikiSite() {
        return wikiSite;
    }

    public void setWikiSite(URL wikiSite) {
        this.wikiSite = wikiSite;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
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

    public ArrayList<Comment> getSpecialistOpinions() {
        return specialistOpinions;
    }

    public void setSpecialistOpinions(ArrayList<Comment> specialistOpinions) {
        this.specialistOpinions = specialistOpinions;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    public ArrayList<News> getScientificStudies() {
        return scientificStudies;
    }

    public void setScientificStudies(ArrayList<News> scientificStudies) {
        this.scientificStudies = scientificStudies;
    }
}
