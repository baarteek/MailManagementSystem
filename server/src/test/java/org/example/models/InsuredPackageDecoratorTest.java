package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsuredPackageDecoratorTest {
    private Package aPackage;
    private InsuredPackageDecorator insuredPackageDecorator;
    private double insuredValue;

    @BeforeEach
    void setUp() {
        aPackage = new Package.Builder()
                .parcelID(123)
                .sender(null)
                .recipient(null)
                .destinationAddress(null)
                .status("Dostarczona")
                .build();
        insuredValue = 1000.0;
        insuredPackageDecorator = new InsuredPackageDecorator(aPackage, insuredValue);
    }

    @Test
    void getInfoShouldIncludeInsuredValue() {
        String info = insuredPackageDecorator.getInfo();
        assertTrue(info.contains("[Ubezpieczone na " + insuredValue + " zl]"));
    }
}