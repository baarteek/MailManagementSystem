package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:mmsdatabase";
    private static final String USER = "c##bartek";
    private static final String PASSWORD = "Bartek123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

