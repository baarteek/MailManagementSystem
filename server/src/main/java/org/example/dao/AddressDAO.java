package org.example.dao;

import org.example.models.Address;
import org.example.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO {
    public Address getAddressById(int addressId) throws SQLException {
        String query = "SELECT * FROM Addresses WHERE address_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, addressId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    String zipCode = rs.getString("zip_code");
                    String street = rs.getString("street");
                    String buildingNumber = rs.getString("building_number");
                    String apartmentNumber = rs.getString("apartment_number");

                    return new Address(country, city, zipCode, street, buildingNumber, apartmentNumber);
                }
            }
        }
        return null;
    }
}
