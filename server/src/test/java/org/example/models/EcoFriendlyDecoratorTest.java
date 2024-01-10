package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EcoFriendlyDecoratorTest {
    private Package aPackage;
    private EcoFriendlyDecorator ecoFriendlyDecorator;

    @BeforeEach
    void setUp() {
        aPackage = new Package.Builder().parcelID(123).sender(null).recipient(null).destinationAddress(null).status("Dostarczona").build();
        ecoFriendlyDecorator = new EcoFriendlyDecorator(aPackage);
    }

    @Test
    void getInfoShouldIncludeEcoFriendlyLabel() {
        String info = ecoFriendlyDecorator.getInfo();
        assertTrue(info.contains("[Paczka ekologiczna]"));
    }

    @Test
    void updateTrackingInfoShouldAppendEcoFriendlyLabel() {
        String newInfo = "W drodze";
        ecoFriendlyDecorator.updateTrackingInfo(newInfo);

        String updatedInfo = ecoFriendlyDecorator.getInfo();
        assertEquals(newInfo + " [Paczka ekologiczna]", updatedInfo);
    }
}