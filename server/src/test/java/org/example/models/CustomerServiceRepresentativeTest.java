package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceRepresentativeTest {
    private CustomerServiceRepresentative csr;
    private Address address;
    private Client client;
    private Parcel parcel;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        csr = new CustomerServiceRepresentative("Jane", "Doe", "123456789", address, 102, "CSR");
        client = new Client("John", "Smith", "987654321", address, 201);
        parcel = new Parcel(123, client, client, address, "Shipped");
    }

    @Test
    void customerServiceRepresentativeShouldBeProperlyCreated() {
        assertNotNull(csr);
        assertEquals("Jane", csr.getFirstName());
        assertEquals("CSR", csr.getRole());
    }

    @Test
    void submitParcelShouldBeCallable() {
        csr.submitParcel(client, parcel);
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void updateParcelInfoShouldBeCallable() {
        csr.updateParcelInfo(client, "123", "NewInfo");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void cancelParcelSubmissionShouldBeCallable() {
        csr.cancelParcelSubmission(client, "123");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void registerClientComplaintShouldBeCallable() {
        csr.registerClientComplaint(client, "ComplaintDetails");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void updateClientDetailsShouldBeCallable() {
        csr.updateClientDetails(client, address, "NewPhoneNumber");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }

    @Test
    void provideTrackingDetailsShouldBeCallable() {
        csr.provideTrackingDetails(client, "123");
        // Test jest pusty, ponieważ metoda nie ma implementacji
    }
}