package org.example.server;

import org.example.utils.DatabaseConnector;

import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (Connection connection = DatabaseConnector.getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

