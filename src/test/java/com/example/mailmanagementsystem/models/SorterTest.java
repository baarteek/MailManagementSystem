package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    private Sorter sorter;
    private Address address;
    private Parcel parcel;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        sorter = new Sorter("Alice", "Smith", "123456789", address, 201, "Sorter");
        parcel = new Parcel(123, null, null, address, "Shipped");
    }

    @Test
    void sorterShouldBeProperlyCreated() {
        assertNotNull(sorter);
        assertEquals("Alice", sorter.getFirstName());
        assertEquals("Sorter", sorter.getRole());
    }

    @Test
    void sortParcelsShouldBeCallable() {
        sorter.sortParcels();
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void assignSortingPriorityShouldBeCallable() {
        sorter.assignSortingPriority(parcel);
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void updateParcelSortingStatusShouldBeCallable() {
        sorter.updateParcelSortingStatus(parcel, "Sorted");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }
}