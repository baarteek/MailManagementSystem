package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        employee = new Employee("Anna", "Nowak", "987654321", address, 202, "Manager");
    }

    @Test
    void getEmployeeIDShouldReturnCorrectID() {
        assertEquals(202, employee.getEmployeeID());
    }

    @Test
    void setEmployeeIDShouldUpdateID() {
        employee.setEmployeeID(203);
        assertEquals(203, employee.getEmployeeID());
    }

    @Test
    void getRoleShouldReturnCorrectRole() {
        assertEquals("Manager", employee.getRole());
    }

    @Test
    void setRoleShouldUpdateRole() {
        employee.setRole("Supervisor");
        assertEquals("Supervisor", employee.getRole());
    }

    @Test
    void employeeShouldInheritPersonProperties() {
        assertEquals("Anna", employee.getFirstName());
        assertEquals("Nowak", employee.getLastName());
        assertEquals("987654321", employee.getPhoneNumber());
        assertEquals(address, employee.getAddress());
    }
}