package com.hieult.foodhub.model;

public class AddressVerModel {
    String addressTitle;
    String addressType;
    String address;
    public AddressVerModel(){}

    public AddressVerModel(String addressTitle, String addressType, String address) {
        this.addressTitle = addressTitle;
        this.addressType = addressType;
        this.address = address;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
