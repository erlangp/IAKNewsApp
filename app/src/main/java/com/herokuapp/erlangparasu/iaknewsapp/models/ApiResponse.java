package com.herokuapp.erlangparasu.iaknewsapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ApiResponse {

    @SerializedName("sortBy")
    private String sortBy;

    @SerializedName("source")
    private String source;

    @SerializedName("articles")
    private ArrayList<Article> articles;

    @SerializedName("status")
    private String status;

    public ApiResponse() {
    }

    public ApiResponse(String sortBy,
                       String source,
                       ArrayList<Article> articles,
                       String status) {
        this.sortBy = sortBy;
        this.source = source;
        this.articles = articles;
        this.status = status;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
