package com.hieult.foodhub.model;

public class FoodHorModel {
    int image;
    String name;
    private boolean isSelect;


    public FoodHorModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public boolean isSelect() {return isSelect;}

    public void setSelect(boolean select) {
        isSelect = select;
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
}
