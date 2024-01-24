package org.example.dao;

import org.example.models.Letter;
import org.example.models.Parcel;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LetterDAO {

    private final ParcelDAO parcelDAO;

    public LetterDAO() {
        this.parcelDAO = new ParcelDAO();
    }

    public Letter getLetterById(int letterId) throws SQLException {
        String query = "SELECT parcel_id, format, is_priority FROM Letters WHERE letter_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, letterId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int parcelId = rs.getInt("parcel_id");
                    String format = rs.getString("format");
                    boolean isPriority = rs.getInt("is_priority") == 1;

                    Parcel parcel = parcelDAO.getParcelById(parcelId);
                    if (parcel != null) {
                        return new Letter.Builder()
                                .parcelID(parcel.getParcelID())
                                .sender(parcel.getSender())
                                .recipient(parcel.getRecipient())
                                .destinationAddress(parcel.getDestinationAddress())
                                .status(parcel.getStatus())
                                .format(format)
                                .isPriority(isPriority)
                                .build();
                    }
                }
            }
        }
        return null;
    }

    public List<String> getLetterInfoByUserId(String userID) throws SQLException {
        List<String> letterInfoList = new ArrayList<>();
        String query = "SELECT\n" +
                "    p.parcel_id AS \"Parcel Number\",\n" +
                "    p.status AS \"Status\",\n" +
                "    p.send_date AS \"Sending Date\",\n" +
                "    p.estimated_delivery_date AS \"Estimated Delivery Date\",\n" +
                "    l.format AS \"Format\",\n" +
                "    l.is_priority AS \"Priority\",\n" +
                "    sender.first_name || ' ' || sender.last_name AS \"Sender\",\n" +
                "    recipient.first_name || ' ' || recipient.last_name AS \"Recipient\"\n" +
                "FROM\n" +
                "    Letters l\n" +
                "    JOIN Parcels p ON l.parcel_id = p.parcel_id\n" +
                "    JOIN Clients sender_client ON p.sender_id = sender_client.client_id\n" +
                "    JOIN Persons sender ON sender_client.person_id = sender.person_id\n" +
                "    JOIN Clients recipient_client ON p.recipient_id = recipient_client.client_id\n" +
                "    JOIN Persons recipient ON recipient_client.person_id = recipient.person_id\n" +
                "    JOIN UserLogins ul ON sender.person_id = ul.person_id\n" +
                "WHERE ul.login_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StringBuilder letterInfo = new StringBuilder();
                    letterInfo.append(rs.getString("Parcel Number")).append(",");
                    letterInfo.append(rs.getString("Status")).append(",");
                    letterInfo.append(rs.getString("Sending Date")).append(",");
                    letterInfo.append(rs.getString("Estimated Delivery Date")).append(",");
                    letterInfo.append(rs.getString("Format")).append(",");
                    letterInfo.append(rs.getInt("Priority") == 1 ? "Yes" : "No").append(",");
                    letterInfo.append(rs.getString("Sender")).append(",");
                    letterInfo.append(rs.getString("Recipient")).append("|");
                    letterInfoList.add(letterInfo.toString());
                }
            }
        }
        return letterInfoList;
    }
}
