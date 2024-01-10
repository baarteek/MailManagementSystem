package org.example.models;

public interface Sortable {
    void sortParcels();
    void assignSortingPriority(Parcel parcel);
    void updateParcelSortingStatus(Parcel parcel, String status);
}
