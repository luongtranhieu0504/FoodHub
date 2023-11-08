package com.hieult.foodhub.model;

public class FoodCartVerModel {
    String nameCart;
    String priceCart;
    String imageCart;
    String numberOrderCart;

    public FoodCartVerModel(String nameCart, String priceCart, String imageCart, String numberOrderCart) {
        this.nameCart = nameCart;
        this.priceCart = priceCart;
        this.imageCart = imageCart;
        this.numberOrderCart = numberOrderCart;
    }

    public String getNumberOrderCart() {
        return numberOrderCart;
    }

    public void setNumberOrderCart(String numberOrderCart) {
        this.numberOrderCart = numberOrderCart;
    }

    public String getNameCart() {
        return nameCart;
    }

    public void setNameCart(String nameCart) {
        this.nameCart = nameCart;
    }

    public String getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(String priceCart) {
        this.priceCart = priceCart;
    }

    public String getImageCart() {
        return imageCart;
    }

    public void setImageCart(String imageCart) {
        this.imageCart = imageCart;
    }
}
