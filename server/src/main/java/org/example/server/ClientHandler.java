package org.example.server;

import org.example.utils.DatabaseConnector;

import javax.swing.plaf.SliderUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

public class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (Connection connection = DatabaseConnector.getConnection();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String commandLine = in.readLine();

            if (commandLine != null) {
                String[] parts = commandLine.split(":");
                String command = parts[0];
                switch (command) {
                    case "LOGIN":
                        String username = parts[1];
                        String password = parts[2];
                        handleLogin(username, password, out);
                        break;
                    case "OTHER_COMMAND":
                        handleOtherCommand(in, out);
                        break;
                    default:
                        out.println("Nieznane polecenie");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleLogin(String username, String password, PrintWriter clientOut) {
        try (PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement("SELECT person_id, role FROM UserLogins WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int personId = rs.getInt("person_id");
                String role = rs.getString("role");
                clientOut.println("SUCCESS:" + personId + ":" + role);
            } else {
                clientOut.println("FAIL");
            }
        } catch (SQLException e) {
            clientOut.println("ERROR");
            e.printStackTrace();
        }
    }



    private void handleOtherCommand(BufferedReader in, PrintWriter out) {
        // Przetwórz inne polecenie
        // Odpowiedź klientowi
        out.println("Odpowiedź na inne polecenie");
    }

}

