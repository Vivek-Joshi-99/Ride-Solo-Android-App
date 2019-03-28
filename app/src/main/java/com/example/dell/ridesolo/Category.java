package com.example.dell.ridesolo;
public class Category {
    private String Vehicle;
    private String Image;
    private String NumberPlate;
    private String Price;

    public Category()
    {}

    public Category(String vehicle, String image, String numberplate, String price) {
        Vehicle = vehicle;
        Image = image;
        NumberPlate = numberplate;
        Price = price;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String vehicle) {
        Vehicle = vehicle;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getNumberPlate() {
        return NumberPlate;
    }

    public void setNumberPlate(String numberplate) {
        NumberPlate = numberplate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}