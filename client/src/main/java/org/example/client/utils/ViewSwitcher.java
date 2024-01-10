package org.example.client.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewSwitcher {

    public static void switchScene(ActionEvent event, String viewName, String cssName, int width, int height, Object thisClass) throws IOException {
        try {
            String viewPath = "/org/example/client/views/" + viewName +".fxml";
            String cssPath = "/org/example/client/css/"+ cssName + ".css";
            Parent root = FXMLLoader.load(Objects.requireNonNull(thisClass.getClass().getResource(viewPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(Objects.requireNonNull(thisClass.getClass().getResource(cssPath)).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
