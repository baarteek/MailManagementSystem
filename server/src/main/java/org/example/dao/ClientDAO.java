package org.example.dao;

import org.example.models.Address;
import org.example.models.Client;
import org.example.models.Person;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

    private final PersonDAO personDAO;

    public ClientDAO() {
        this.personDAO = new PersonDAO();
    }

    public Client getClientById(int clientId) throws SQLException {
        String query = "SELECT person_id FROM Clients WHERE client_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int personId = rs.getInt("person_id");

                    Person person = personDAO.getPersonById(personId);
                    if (person != null) {
                        return new Client(person.getFirstName(), person.getLastName(),
                                person.getPhoneNumber(), person.getAddress(), clientId);
                    }
                }
            }
        }
        return null;
    }
}
