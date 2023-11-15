package com.example.mailmanagementsystem.models;

public class Client extends Person implements RemoteClientActions{
    private int clientID;
    private String deliveryPreferences;

    public Client(String firstName, String lastName, String phoneNumber, Address address, int clientID, String deliveryPreferences) {
        super(firstName, lastName, phoneNumber, address);
        this.clientID = clientID;
        this.deliveryPreferences = deliveryPreferences;
    }

    @Override
    public void loginToSystem(String username, String password) {
        //login
    }

    @Override
    public void trackParcel(int parcelID) {
        //track parcel
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getDeliveryPreferences() {
        return deliveryPreferences;
    }

    public void setDeliveryPreferences(String deliveryPreferences) {
        this.deliveryPreferences = deliveryPreferences;
    }
}
