package com.example.mailmanagementsystem;

import com.example.mailmanagementsystem.models.Address;
import com.example.mailmanagementsystem.models.Client;
import com.example.mailmanagementsystem.models.Letter;
import com.example.mailmanagementsystem.models.Package;

public class Main {
    public static void main(String[] args) {
        Client sender = new Client("Jan", "Kowalski", "500100500", new Address("Polska", "Kielce", "20-200", "Warszawska", "2", "2"), 1);
        Client recipient = new Client("Dorota", "Nowak", "500100400", new Address("Polska", "Warszawa", "20-300", "Swietokrzyska", "2", "2"), 1);

        // Test wzorca budowniczy w klasie Letter
        Letter letter = new Letter.Builder().parcelID(1).sender(sender).recipient(recipient).destinationAddress(recipient.getAddress()).status("sent").format("A4").isPriority(true).build();

        // Test wzoraca budowniczy w klasie Package
        Package package1 = new Package.Builder().parcelID(1).sender(sender).recipient(recipient).destinationAddress(recipient.getAddress()).status("sent").weight(10.1).height(10.2).width(12.2).length(5.5).isInsured(true).build();
    }
}
