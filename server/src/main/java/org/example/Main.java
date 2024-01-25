package org.example;

import org.example.models.*;
import org.example.models.Package;
import org.example.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

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



        // Polaczenei z baza danych
        try (Connection conn = DatabaseConnector.getConnection()) {
            System.out.println("Połączenie z bazą danych zostało nawiązane pomyślnie.");
        } catch (SQLException e) {
            System.out.println("Nie udało się nawiązać połączenia z bazą danych.");
            e.printStackTrace();
        }
    }
}
