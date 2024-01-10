package org.example.models;

public interface Deliverable {
    void deliverParcel(Parcel parcel);
    void updateDeliveryStatus(Parcel parcel, String status);
    void returnParcel(Parcel parcel);
}
