package com.example.mailmanagementsystem.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FragilePackageDecoratorTest {
    private Package aPackage;
    private FragilePackageDecorator fragilePackageDecorator;

    @BeforeEach
    void setUp() {
        aPackage = new Package.Builder()
                .parcelID(123)
                .sender(null)
                .recipient(null)
                .destinationAddress(null)
                .status("Dostarczona")
                .build();
        fragilePackageDecorator = new FragilePackageDecorator(aPackage);
    }

    @Test
    void getInfoShouldIncludeFragileLabel() {
        String info = fragilePackageDecorator.getInfo();
        assertTrue(info.contains("[Uwaga - Kruche]"));
    }

}