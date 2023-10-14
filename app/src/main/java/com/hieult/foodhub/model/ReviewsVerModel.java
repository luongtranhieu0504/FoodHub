package com.hieult.foodhub.model;

public class ReviewsVerModel {
    int image;
    String rating;
    String name;
    String date;
    String comment;

    public ReviewsVerModel(int image, String rating, String name, String date, String comment) {
        this.image = image;
        this.rating = rating;
        this.name = name;
        this.date = date;
        this.comment = comment;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
