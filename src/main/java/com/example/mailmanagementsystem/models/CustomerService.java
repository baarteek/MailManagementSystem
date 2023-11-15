package com.example.mailmanagementsystem.models;

public interface CustomerService {
    void submitParcel(Client client, Parcel parcel);
    void updateParcelInfo(Client client, String parcelID, String newInfo);
    void cancelParcelSubmission(Client client, String parcelID);
    void registerClientComplaint(Client client, String complaintDetails);
    void updateClientDetails(Client client, Address newAddress, String newPhoneNumber);
    void provideTrackingDetails(Client client, String parcelID);
}