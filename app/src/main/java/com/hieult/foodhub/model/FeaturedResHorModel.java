package com.hieult.foodhub.model;

public class FeaturedResHorModel {
    int image;
    String name;
    String Delivery;
    String time;
    String rate;

    public FeaturedResHorModel(int image, String name, String delivery, String time, String rate) {
        this.image = image;
        this.name = name;
        Delivery = delivery;
        this.time = time;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDelivery() {
        return Delivery;
    }

    public void setDelivery(String delivery) {
        Delivery = delivery;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
