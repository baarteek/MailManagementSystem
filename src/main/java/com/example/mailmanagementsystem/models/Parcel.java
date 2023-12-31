package com.example.mailmanagementsystem.models;

public class Parcel implements Trackable {
    private int parcelID;
    private Client sender;
    private Client recipient;
    private Address destinationAddress;
    private String status;

    public Parcel(int parcelID, Client sender, Client recipient, Address destinationAddress, String status) {
        this.parcelID = parcelID;
        this.sender = sender;
        this.recipient = recipient;
        this.destinationAddress = destinationAddress;
        this.status = status;
    }

    @Override
    public void updateTrackingInfo(String status) {

    }

    public int getParcelID() {
        return parcelID;
    }

    public void setParcelID(int parcelID) {
        this.parcelID = parcelID;
    }

    public Client getSender() {
        return sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public Client getRecipient() {
        return recipient;
    }

    public void setRecipient(Client recipient) {
        this.recipient = recipient;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
