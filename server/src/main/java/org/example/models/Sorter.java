package org.example.models;

public class Sorter extends Employee implements Sortable{

    public Sorter(String firstName, String lastName, String phoneNumber, Address address, int employeeID, String role) {
        super(firstName, lastName, phoneNumber, address, employeeID, role);
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
