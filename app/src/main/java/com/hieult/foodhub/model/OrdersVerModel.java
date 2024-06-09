package com.hieult.foodhub.model;

public class OrdersVerModel {
    int image;
    String items;
    String id;
    String name;
    String time;
    String state;

    public OrdersVerModel(int image, String items, String id, String name, String time, String state) {
        this.image = image;
        this.items = items;
        this.id = id;
        this.name = name;
        this.time = time;
        this.state = state;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
