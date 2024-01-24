package org.example.dao;

import org.example.models.Letter;
import org.example.models.Parcel;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
