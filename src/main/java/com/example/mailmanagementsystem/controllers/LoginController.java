package com.example.mailmanagementsystem.controllers;

import com.example.mailmanagementsystem.utils.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class LoginController {
    @FXML
    private RadioButton radioButtonClient;
    @FXML
    private RadioButton radioButtonEmployee;
    @FXML
    private RadioButton radioButtonAdmin;

    public void login(ActionEvent event) throws IOException {
        if(radioButtonClient.isSelected()) {
            ViewSwitcher.switchScene(event, "client-view", "client-style", 800, 600,this);
        } else if(radioButtonEmployee.isSelected()) {
            ViewSwitcher.switchScene(event, "sorter-view", "employee-style", 800, 600,this);
        } else if(radioButtonAdmin.isSelected()) {

        }
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        ViewSwitcher.switchScene(event, "register-view", "login-style",800, 800, this);
    }
}
