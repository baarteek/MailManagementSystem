package com.example.mailmanagementsystem.models;

public class Package extends Parcel{
    private double weight;
    private double height;
    private double width;
    private  double length;
    private boolean isInsured;

    private Package(int parcelID, Client sender, Client recipient, Address destinationAddress, String status, double weight, double height, double width, double length, boolean isInsured) {
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

    public static class Builder {
        private int parcelID;
        private Client sender;
        private Client recipient;
        private Address destinationAddress;
        private String status;
        private double weight = 1.0;
        private double height = 1.0;
        private double width = 1.0;
        private double length = 1.0;
        private boolean isInsured = false;

        public Package build() {
            return new Package(parcelID, sender, recipient, destinationAddress, status, weight, height, width, length, isInsured);
        }

        public Builder parcelID(int parcelID) {
            this.parcelID = parcelID;
            return this;
        }

        public Builder sender(Client sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(Client recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder destinationAddress(Address destinationAddress) {
            this.destinationAddress = destinationAddress;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder height(double height) {
            this.height = height;
            return this;
        }

        public Builder width(double width) {
            this.width = width;
            return this;
        }

        public Builder length(double length) {
            this.length = length;
            return this;
        }

        public Builder isInsured(boolean isInsured) {
            this.isInsured = isInsured;
            return this;
        }
    }

}
