package com.example.mailmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/login-view.fxml")));
            Scene loginScene = new Scene(root, 800, 500);
            loginScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/login-style.css")).toExternalForm());

            stage.setMinWidth(650);
            stage.setMinHeight(550);
            stage.setTitle("Mail Management System");
            stage.setScene(loginScene);
            stage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}