package org.example.dao;

import org.example.models.Package;
import org.example.models.Parcel;
import org.example.utils.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getPackageInfoByUserId(String userID) throws SQLException {
        List<String> packageInfoList = new ArrayList<>();
        String query = "SELECT " +
                "p.parcel_id AS \"Parcel Number\", " +
                "p.status AS \"Status\", " +
                "p.send_date AS \"Sending Date\", " +
                "p.estimated_delivery_date AS \"Estimated Delivery Date\", " +
                "p.is_express_delivery AS \"Express Delivery\", " +
                "p.is_fragile AS \"Fragile\", " +
                "p.insured_value AS \"Insurance Value\", " +
                "s.first_name || ' ' || s.last_name AS \"Sender\", " +
                "r.first_name || ' ' || r.last_name AS \"Recipient\", " +
                "pkg.weight AS \"Weight\", " +
                "pkg.height || 'x' || pkg.width || 'x' || pkg.length AS \"Dimensions\", " +
                "pkg.info AS \"Information\" " +
                "FROM Parcels p " +
                "JOIN Clients sender ON p.sender_id = sender.client_id " +
                "JOIN Persons s ON sender.person_id = s.person_id " +
                "JOIN Clients recipient ON p.recipient_id = recipient.client_id " +
                "JOIN Persons r ON recipient.person_id = r.person_id " +
                "JOIN Packages pkg ON p.parcel_id = pkg.parcel_id " +
                "JOIN UserLogins ul ON s.person_id = ul.person_id " +
                "WHERE ul.login_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StringBuilder packageInfo = new StringBuilder();
                    packageInfo.append(rs.getString("Parcel Number")).append(",");
                    packageInfo.append(rs.getString("Status")).append(",");
                    packageInfo.append(rs.getString("Sending Date")).append(",");
                    packageInfo.append(rs.getString("Estimated Delivery Date")).append(",");
                    packageInfo.append(rs.getInt("Express Delivery") == 1 ? "Yes" : "No").append(",");
                    packageInfo.append(rs.getInt("Fragile") == 1 ? "Yes" : "No").append(",");
                    packageInfo.append(rs.getDouble("Insurance Value")).append(",");
                    packageInfo.append(rs.getString("Sender")).append(",");
                    packageInfo.append(rs.getString("Recipient")).append(",");
                    packageInfo.append(rs.getDouble("Weight")).append(",");
                    packageInfo.append(rs.getString("Dimensions")).append(",");
                    packageInfo.append(rs.getString("Information")).append("|");
                    packageInfoList.add(packageInfo.toString());
                }
            }
        }
        return packageInfoList;
    }

}
