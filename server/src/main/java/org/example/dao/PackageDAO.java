package org.example.dao;

import org.example.models.Address;
import org.example.models.Client;
import org.example.models.Package;
import org.example.models.Parcel;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageDAO {

    private final ParcelDAO parcelDAO;

    public PackageDAO() {
        this.parcelDAO = new ParcelDAO();
    }

    public Package getPackageById(int packageId) throws SQLException {
        String query = "SELECT parcel_id, weight, height, width, length, info FROM Packages WHERE package_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, packageId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int parcelId = rs.getInt("parcel_id");
                    double weight = rs.getDouble("weight");
                    double height = rs.getDouble("height");
                    double width = rs.getDouble("width");
                    double length = rs.getDouble("length");
                    String info = rs.getString("info");

                    Parcel parcel = parcelDAO.getParcelById(parcelId);
                    if (parcel != null) {
                        return new Package.Builder()
                                .parcelID(parcel.getParcelID())
                                .sender(parcel.getSender())
                                .recipient(parcel.getRecipient())
                                .destinationAddress(parcel.getDestinationAddress())
                                .status(parcel.getStatus())
                                .weight(weight)
                                .height(height)
                                .width(width)
                                .length(length)
                                .info(info)
                                .build();
                    }
                }
            }
        }

        return null;
    }
}
