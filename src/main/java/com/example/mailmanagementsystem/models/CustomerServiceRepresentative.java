package com.example.mailmanagementsystem.models;

public class CustomerServiceRepresentative extends Employee implements CustomerService{

    public CustomerServiceRepresentative(String firstName, String lastName, String phoneNumber, Address address, int employeeID, String role) {
        super(firstName, lastName, phoneNumber, address, employeeID, role);
    }

    @Override
    public void submitParcel(Client client, Parcel parcel) {

    }

    @Override
    public void updateParcelInfo(Client client, String parcelID, String newInfo) {

    }

    @Override
    public void cancelParcelSubmission(Client client, String parcelID) {

    }

    @Override
    public void registerClientComplaint(Client client, String complaintDetails) {

    }

    @Override
    public void updateClientDetails(Client client, Address newAddress, String newPhoneNumber) {

    }

    @Override
    public void provideTrackingDetails(Client client, String parcelID) {

    }
}
