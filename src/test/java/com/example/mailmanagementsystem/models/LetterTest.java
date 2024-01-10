package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetterTest {
    private Letter letter;
    private Client sender;
    private Client recipient;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("Country", "City", "ZipCode", "Street", "BuildingNumber", "ApartmentNumber");
        sender = new Client("John", "Doe", "123456789", address, 101);
        recipient = new Client("Jane", "Doe", "987654321", address, 102);

        letter = new Letter.Builder()
                .parcelID(1)
                .sender(sender)
                .recipient(recipient)
                .destinationAddress(address)
                .status("Shipped")
                .format("A4")
                .isPriority(true)
                .build();
    }

    @Test
    void letterShouldBeCorrectlyCreatedWithBuilder() {
        assertNotNull(letter);
        assertEquals("A4", letter.getFormat());
        assertTrue(letter.isPriority());
        assertEquals(sender, letter.getSender());
        assertEquals(recipient, letter.getRecipient());
        assertEquals(address, letter.getDestinationAddress());
        assertEquals("Shipped", letter.getStatus());
        assertEquals("A4", letter.getFormat());
    }

    @Test
    void setFormatShouldUpdateFormat() {
        letter.setFormat("A3");
        assertEquals("A3", letter.getFormat());
    }

    @Test
    void setPriorityShouldUpdatePriority() {
        letter.setPriority(false);
        assertFalse(letter.isPriority());
    }
}