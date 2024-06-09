package com.hieult.foodhub.data;

public class HelperAddressClass {
    String name;
    String phoneNumber;
    String addressTitle;
    String addressType;
    String city;
    String street;

    public HelperAddressClass(String name, String phoneNumber, String addressTitle, String addressType, String city, String street) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressTitle = addressTitle;
        this.addressType = addressType;
        this.city = city;
        this.street = street;
    }

    public HelperAddressClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
