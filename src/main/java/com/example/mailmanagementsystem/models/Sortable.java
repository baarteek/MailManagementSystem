package com.example.mailmanagementsystem.models;

public interface Sortable {
    void sortParcels();
    void assignSortingPriority(Parcel parcel);
    void updateParcelSortingStatus(Parcel parcel, String status);
}
