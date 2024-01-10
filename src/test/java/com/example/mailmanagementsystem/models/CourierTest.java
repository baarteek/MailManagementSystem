package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {
    private Courier courier;
    private Address address;
    private Parcel parcel;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        courier = new Courier("John", "Doe", "123456789", address, 101, "Courier");
        parcel = new Parcel(123, null, null, address, "Shipped");
    }

    @Test
    void courierShouldBeProperlyCreated() {
        assertNotNull(courier);
        assertEquals("John", courier.getFirstName());
        assertEquals("Courier", courier.getRole());
    }

    @Test
    void deliverParcelShouldBeCallable() {
        courier.deliverParcel(parcel);
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void updateDeliveryStatusShouldBeCallable() {
        courier.updateDeliveryStatus(parcel, "Delivered");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void returnParcelShouldBeCallable() {
        courier.returnParcel(parcel);
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }
}