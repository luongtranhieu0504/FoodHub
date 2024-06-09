package com.hieult.foodhub.model;

public class FoodPopularHorModel {
    String image;
    String price;
    String rate;
    String name;

    public FoodPopularHorModel(String image, String price, String rate, String name) {
        this.image = image;
        this.price = price;
        this.rate = rate;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
