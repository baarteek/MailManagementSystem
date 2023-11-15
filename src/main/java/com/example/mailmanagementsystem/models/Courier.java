package com.example.mailmanagementsystem.models;

public class Courier implements Deliverable{
    private Employee employee;

    public Courier(Employee employee) {
        this.employee = employee;
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
