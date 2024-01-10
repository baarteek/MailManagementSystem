package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressDeliveryDecoratorTest {
    private Package aPackage;
    private ExpressDeliveryDecorator expressDeliveryDecorator;

    @BeforeEach
    void setUp() {
        aPackage = new Package.Builder()
                .parcelID(123)
                .sender(null)
                .recipient(null)
                .destinationAddress(null)
                .status("Dostarczona")
                .build();
        expressDeliveryDecorator = new ExpressDeliveryDecorator(aPackage);
    }

    @Test
    void getInfoShouldIncludeExpressDeliveryLabel() {
        String info = expressDeliveryDecorator.getInfo();
        assertTrue(info.contains("[Dostawa Ekspresowa]"));
    }
}