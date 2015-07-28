package com.binitshah.medfieldtv;

import java.util.ArrayList;

/**
 * Created by Binit on 7/10/15.
 */
public class Show {
    private String title, thumbnailUrl, description, times;
    private ArrayList<String> genre;

    public Show() {
    }

    public Show(String name, String thumbnaillUrl, String descriptionn, String lengthofshow,
                 ArrayList<String> genre) {
        this.title = name;
        this.thumbnailUrl = thumbnaillUrl;
        this.description = descriptionn;
        this.times = lengthofshow;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String tempDescription) {
        this.description = tempDescription;
    }

    public String getTimes() {
        return times;
    }

    public void setDuration(String tempDuration) {
        this.times = tempDuration;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}