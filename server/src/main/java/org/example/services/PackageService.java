package org.example.services;

import org.example.dao.PackageDAO;
import org.example.dao.PersonDAO;
import org.example.server.Server;
import org.example.utils.DatabaseConnector;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PackageService {
    public void sendPackageInfo(String userID, PrintWriter clientOut) {
        PackageDAO packageDAO = new PackageDAO();
        try {
            int id = Integer.parseInt(userID);
            Server.addClientConnection(id, clientOut);
            List<String> packageInfoList = packageDAO.getPackageInfoByUserId(userID);
            StringBuilder packageInfo = new StringBuilder();
            for (String info : packageInfoList) {
                packageInfo.append(info);
                System.out.println(packageInfo);
            }
            clientOut.println(packageInfo);
        } catch (SQLException e) {
            clientOut.println("ERROR");
            e.printStackTrace();
        }
    }
}
