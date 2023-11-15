package com.example.mailmanagementsystem.models;

public class Sorter implements Sortable{
    private Employee employee;

    public Sorter(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void sortParcels() {

    }

    @Override
    public void assignSortingPriority(Parcel parcel) {

    }

    @Override
    public void updateParcelSortingStatus(Parcel parcel, String status) {

    }
}
