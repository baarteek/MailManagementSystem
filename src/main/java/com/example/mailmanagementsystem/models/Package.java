package com.example.mailmanagementsystem.models;

public class Package extends Parcel{
    private double weight;
    private double height;
    private double width;
    private  double length;
    private boolean isInsured;

    public Package(int parcelID, Client sender, Client recipient, Address destinationAddress, String status, double weight, double height, double width, double length, boolean isInsured) {
        super(parcelID, sender, recipient, destinationAddress, status);
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.isInsured = isInsured;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean insured) {
        isInsured = insured;
    }
}
