package com.hieult.foodhub.model;

public class FoodSearchVerModel {
    String image;
    String name;
    String delivery;
    String time;
    String rating;
    String description;
    String price;
    public FoodSearchVerModel(){}

    public FoodSearchVerModel(String image, String name, String delivery, String time, String rating, String description, String price) {
        this.image = image;
        this.name = name;
        this.delivery = delivery;
        this.time = time;
        this.rating = rating;
        this.description = description;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
