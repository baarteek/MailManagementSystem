package com.example.mailmanagementsystem.models;

public class CustomerServiceRepresentative implements CustomerService{
    private Employee employee;

    public CustomerServiceRepresentative(Employee employee) {
        this.employee = employee;
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
