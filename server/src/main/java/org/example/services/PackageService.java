package org.example.services;

import org.example.utils.DatabaseConnector;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageService {
    public void sendPackageInfo(String userID, PrintWriter clientOut) {
        try (PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT\n" +
                "    p.parcel_id AS \"Parcel Number\",\n" +
                "    p.status AS \"Status\",\n" +
                "    p.send_date AS \"Sending Date\",\n" +
                "    p.estimated_delivery_date AS \"Estimated Delivery Date\",\n" +
                "    p.is_express_delivery AS \"Express Delivery\",\n" +
                "    p.is_fragile AS \"Fragile\",\n" +
                "    p.insured_value AS \"Insurance Value\",\n" +
                "    s.first_name || ' ' || s.last_name AS \"Sender\",\n" +
                "    r.first_name || ' ' || r.last_name AS \"Recipient\",\n" +
                "    pkg.weight AS \"Weight\",\n" +
                "    pkg.height || 'x' || pkg.width || 'x' || pkg.length AS \"Dimensions\",\n" +
                "    pkg.info AS \"Information\"\n" +
                "FROM\n" +
                "    Parcels p\n" +
                "    JOIN Clients sender ON p.sender_id = sender.client_id\n" +
                "    JOIN Persons s ON sender.person_id = s.person_id\n" +
                "    JOIN Clients recipient ON p.recipient_id = recipient.client_id\n" +
                "    JOIN Persons r ON recipient.person_id = r.person_id\n" +
                "    JOIN Packages pkg ON p.parcel_id = pkg.parcel_id\n" +
                "    JOIN UserLogins ul ON s.person_id = ul.person_id -- Dodane połączenie z UserLogins\n" +
                "WHERE\n" +
                "    ul.login_id = ?")) {
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();
            StringBuilder packageInfo = new StringBuilder();

            while (rs.next()) {
                packageInfo.append(rs.getString("Parcel Number")).append(",");
                packageInfo.append(rs.getString("Status")).append(",");
                packageInfo.append(rs.getString("Sending Date")).append(",");
                packageInfo.append(rs.getString("Estimated Delivery Date")).append(",");
                packageInfo.append(rs.getString("Express Delivery")).append(",");
                packageInfo.append(rs.getString("Fragile")).append(",");
                packageInfo.append(rs.getString("Insurance Value")).append(",");
                packageInfo.append(rs.getString("Sender")).append(",");
                packageInfo.append(rs.getString("Recipient")).append(",");
                packageInfo.append(rs.getString("Weight")).append(",");
                packageInfo.append(rs.getString("Dimensions")).append(",");
                packageInfo.append(rs.getString("Information")).append("|");
            }

            System.out.println(packageInfo.toString());
            clientOut.println(packageInfo.toString());

        } catch (SQLException e) {
            clientOut.println("ERROR");
            e.printStackTrace();
        }
    }
}
