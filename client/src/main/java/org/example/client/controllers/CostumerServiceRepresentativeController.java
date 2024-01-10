package org.example.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.example.client.utils.ViewSwitcher;
import java.io.IOException;

public class CostumerServiceRepresentativeController {
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mainTab;


    public void backToLogin(ActionEvent event) throws IOException {
        ViewSwitcher.switchScene(event, "login-view", "login-style",800, 500, this);
    }

    public void backToMainView(ActionEvent event) {
        tabPane.getSelectionModel().select(mainTab);
    }
}
