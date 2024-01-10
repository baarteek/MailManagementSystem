package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ClientNotificationSystemTest {
    private ClientNotificationSystem clientNotificationSystem;
    private Parcel parcel;
    private Client client;
    private Address address;

    @BeforeEach
    void setUp() {
        clientNotificationSystem = new ClientNotificationSystem();
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        client = new Client("John", "Doe", "123456789", address, 101);
        parcel = new Parcel(123, client, client, address, "Shipped");
    }

    @Test
    void updateShouldReflectChangeInParcelStatus() {
        String newStatus = "Dostarczono";
        parcel.setStatus(newStatus);

        clientNotificationSystem.update(parcel);

        assertEquals(newStatus, parcel.getStatus());
    }
}