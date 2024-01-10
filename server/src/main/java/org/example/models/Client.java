package org.example.models;

public class Client extends Person implements RemoteClientActions{
    private int clientID;


    public Client(String firstName, String lastName, String phoneNumber, Address address, int clientID) {
        super(firstName, lastName, phoneNumber, address);
        this.clientID = clientID;
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
}
