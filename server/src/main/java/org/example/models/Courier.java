package org.example.models;

public class Courier extends Employee implements Deliverable{


    public Courier(String firstName, String lastName, String phoneNumber, Address address, int employeeID, String role) {
        super(firstName, lastName, phoneNumber, address, employeeID, role);
    }

    @Override
    public void deliverParcel(Parcel parcel) {

    }

    @Override
    public void updateDeliveryStatus(Parcel parcel, String status) {

    }

    @Override
    public void returnParcel(Parcel parcel) {

    }
}
