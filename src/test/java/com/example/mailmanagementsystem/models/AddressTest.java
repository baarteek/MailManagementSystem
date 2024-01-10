package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    void testAddressCreation() {
        Address address = new Address("Poland", "Warsaw", "00-001", "Marszalkowska", "10", "2A");
        assertNotNull(address);
        assertEquals("Poland", address.getCountry());
        assertEquals("Warsaw", address.getCity());
        assertEquals("00-001", address.getZipCode());
        assertEquals("Marszalkowska", address.getStreet());
        assertEquals("10", address.getBuildingNumber());
        assertEquals("2A", address.getApartmentNumber());
    }

    @Test
    void testSettersAndGetters() {
        Address address = new Address("Poland", "Warsaw", "00-001", "Marszałkowska", "10", "2A");
        address.setCity("Krakow");
        assertEquals("Krakow", address.getCity());
        address.setCountry("USA");
        assertEquals("USA", address.getCountry());
        address.setZipCode("11-110");
        assertEquals("11-110", address.getZipCode());
        address.setStreet("Wiejska");
        assertEquals("Wiejska", address.getStreet());
        address.setBuildingNumber("88");
        assertEquals("88", address.getBuildingNumber());
        address.setApartmentNumber("33C");
        assertEquals("33C", address.getApartmentNumber());
    }
}