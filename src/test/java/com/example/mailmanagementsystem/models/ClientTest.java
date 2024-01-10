package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        client = new Client("Jan", "Kowalski", "123456789", address, 101);
    }

    @Test
    void getClientIDShouldReturnCorrectID() {
        assertEquals(101, client.getClientID());
    }

    @Test
    void setClientIDShouldUpdateID() {
        client.setClientID(102);
        assertEquals(102, client.getClientID());
    }

    @Test
    void clientShouldInheritPersonProperties() {
        assertEquals("Jan", client.getFirstName());
        assertEquals("Kowalski", client.getLastName());
        assertEquals("123456789", client.getPhoneNumber());
        assertEquals(address, client.getAddress());
    }
}