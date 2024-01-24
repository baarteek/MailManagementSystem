package org.example.dao;

import org.example.models.Address;
import org.example.models.Client;
import org.example.models.Parcel;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelDAO {

    private final ClientDAO clientDAO;
    private final AddressDAO addressDAO;

    public ParcelDAO() {
        this.clientDAO = new ClientDAO();
        this.addressDAO = new AddressDAO();
    }

    public Parcel getParcelById(int parcelId) throws SQLException {
        String query = "SELECT sender_id, recipient_id, destination_address_id, status FROM Parcels WHERE parcel_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, parcelId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int senderId = rs.getInt("sender_id");
                    int recipientId = rs.getInt("recipient_id");
                    int destinationAddressId = rs.getInt("destination_address_id");
                    String status = rs.getString("status");

                    Client sender = clientDAO.getClientById(senderId);
                    Client recipient = clientDAO.getClientById(recipientId);
                    Address destinationAddress = addressDAO.getAddressById(destinationAddressId);

                    return new Parcel(parcelId, sender, recipient, destinationAddress, status);
                }
            }
        }

        return null;
    }
}
