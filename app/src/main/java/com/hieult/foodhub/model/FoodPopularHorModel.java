package com.hieult.foodhub.model;

public class FoodPopularHorModel {
    int image;
    String price;
    String rate;
    String name;
    String subName;

    public FoodPopularHorModel(int image, String price, String rate, String name, String subName) {
        this.image = image;
        this.price = price;
        this.rate = rate;
        this.name = name;
        this.subName = subName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
