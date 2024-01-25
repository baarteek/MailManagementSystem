package org.example.server;

import org.example.services.LetterService;
import org.example.services.PackageService;
import org.example.services.ParcelService;
import org.example.utils.DatabaseConnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

import static org.example.server.Server.sendNotificationToClient;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final ParcelService parcelService = new ParcelService();
    private final PackageService packageService = new PackageService();
    private final LetterService letterService = new LetterService();
    private Integer clientId = null;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String commandLine;

            while ((commandLine = in.readLine()) != null) {
                String[] parts = commandLine.split(":");
                String command = parts[0];
                switch (command) {
                    case "LOGIN":
                        String username = parts[1];
                        String password = parts[2];
                        handleLogin(username, password, out);
                        break;
                    case "PACKAGE_INFO":
                        String userID = parts[1];
                        packageService.sendPackageInfo(userID, out);
                        break;
                    case "LETTER_INFO":
                        letterService.sendLetterInfo(parts[1], out);
                        break;
                    case "TRACK_PARCEL":
                        String parcelID = parts[1];
                        parcelService.trackParcel(parcelID, clientId, out);
                        break;
                    case "TEST_CHANGE_STATUS":
                        int parcelId = Integer.parseInt(parts[1]);
                        String newStatus = parts[2];
                        parcelService.testChangeParcelStatus(parcelId, newStatus);
                        break;
                    case "DISCONNECT":
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(String username, String password, PrintWriter clientOut) {
        try (PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT person_id, role FROM UserLogins WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                clientId = rs.getInt("person_id");
                String role = rs.getString("role");
                clientOut.println("SUCCESS:" + clientId + ":" + role);
            } else {
                clientOut.println("FAIL");
            }
        } catch (SQLException e) {
            clientOut.println("ERROR");
            e.printStackTrace();
        }
    }
}
