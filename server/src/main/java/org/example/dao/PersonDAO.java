package org.example.dao;

import org.example.models.Address;
import org.example.models.Person;
import org.example.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO {
    private final AddressDAO addressDAO;

    public PersonDAO() {
        this.addressDAO = new AddressDAO();
    }

    public Person getPersonById(int personId) throws SQLException {
        String query = "SELECT * FROM Persons WHERE person_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, personId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String phoneNumber = rs.getString("phone_number");
                    int addressId = rs.getInt("address_id");

                    Address address = addressDAO.getAddressById(addressId);

                    return new Person(firstName, lastName, phoneNumber, address);
                }
            }
        }
        return null;
    }
}
