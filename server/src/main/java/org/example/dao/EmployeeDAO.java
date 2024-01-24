package org.example.dao;

import org.example.models.Address;
import org.example.models.Employee;
import org.example.models.Person;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    private final PersonDAO personDAO;

    public EmployeeDAO() {
        this.personDAO = new PersonDAO();
    }

    public Employee getEmployeeById(int employeeId) throws SQLException {
        String query = "SELECT person_id, role FROM Employees WHERE employee_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, employeeId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int personId = rs.getInt("person_id");
                    String role = rs.getString("role");

                    Person person = personDAO.getPersonById(personId);
                    if (person != null) {
                        return new Employee(person.getFirstName(), person.getLastName(),
                                person.getPhoneNumber(), person.getAddress(), employeeId, role);
                    }
                }
            }
        }

        return null;
    }
}
