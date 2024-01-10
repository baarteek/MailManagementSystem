package com.example.mailmanagementsystem;

import com.example.mailmanagementsystem.models.*;
import com.example.mailmanagementsystem.models.Package;

public class Main {
    public static void main(String[] args) {
        Client sender = new Client("Jan", "Kowalski", "500100500", new Address("Polska", "Kielce", "20-200", "Warszawska", "2", "2"), 1);
        Client recipient = new Client("Dorota", "Nowak", "500100400", new Address("Polska", "Warszawa", "20-300", "Swietokrzyska", "2", "2"), 1);

        // Wzorzec budowniczy w klasie Letter
        Letter letter = new Letter.Builder().parcelID(1).sender(sender).recipient(recipient).destinationAddress(recipient.getAddress()).status("Wyslany").format("A4").isPriority(true).build();

        // Wzorzec budowniczy w klasie Package
        Package package1 = new Package.Builder().parcelID(1).sender(sender).recipient(recipient).destinationAddress(recipient.getAddress()).status("Wyslany").weight(10.1).height(10.2).width(12.2).length(5.5).build();

        // Wzorzec dekorator
        PackageDecorator fragilePackage = new FragilePackageDecorator(package1);
        PackageDecorator insuredPackage = new InsuredPackageDecorator(package1, 100);
        PackageDecorator expressPackage = new ExpressDeliveryDecorator(package1);
        PackageDecorator ecoPackage = new EcoFriendlyDecorator(package1);
        System.out.println(package1.getInfo());

        // Wzorzec obserwator
        ClientNotificationSystem cns = new ClientNotificationSystem();
        package1.registerObserver(cns);
        package1.setStatus("W transporcie");
    }
}
