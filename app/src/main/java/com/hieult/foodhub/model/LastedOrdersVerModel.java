package com.hieult.foodhub.model;

public class LastedOrdersVerModel {
    int image;
    String date;
    String items;
    String price;;
    String name;
    String state;

    public LastedOrdersVerModel(int image, String date, String items, String price, String name, String state) {
        this.image = image;
        this.date = date;
        this.items = items;
        this.price = price;
        this.name = name;
        this.state = state;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
