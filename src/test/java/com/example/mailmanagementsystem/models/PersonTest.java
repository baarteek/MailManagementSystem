package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        person = new Person("John", "Doe", "123456789", address);
    }

    @Test
    void getFirstNameShouldReturnCorrectFirstName() {
        assertEquals("John", person.getFirstName());
    }

    @Test
    void setFirstNameShouldUpdateFirstName() {
        person.setFirstName("Jane");
        assertEquals("Jane", person.getFirstName());
    }

    @Test
    void getLastNameShouldReturnCorrectLastName() {
        assertEquals("Doe", person.getLastName());
    }

    @Test
    void setLastNameShouldUpdateLastName() {
        person.setLastName("Smith");
        assertEquals("Smith", person.getLastName());
    }

    @Test
    void getPhoneNumberShouldReturnCorrectPhoneNumber() {
        assertEquals("123456789", person.getPhoneNumber());
    }

    @Test
    void setPhoneNumberShouldUpdatePhoneNumber() {
        person.setPhoneNumber("987654321");
        assertEquals("987654321", person.getPhoneNumber());
    }

    @Test
    void getAddressShouldReturnCorrectAddress() {
        assertEquals(address, person.getAddress());
    }

    @Test
    void setAddressShouldUpdateAddress() {
        Address newAddress = new Address("NewCountry", "NewCity", "NewZipCode", "NewStreet", "NewBuildingNumber", "NewApartmentNumber");
        person.setAddress(newAddress);
        assertEquals(newAddress, person.getAddress());
    }
}