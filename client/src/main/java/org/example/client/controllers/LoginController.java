package org.example.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.example.client.utils.GlobalState;
import org.example.client.utils.ServerClient;
import org.example.client.utils.ViewSwitcher;

import java.io.IOException;

public class LoginController {
    @FXML
    private RadioButton radioButtonClient;
    @FXML
    private RadioButton radioButtonEmployee;
    @FXML
    private RadioButton radioButtonAdmin;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label errorLabel;

    public void login(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        ServerClient serverClient = new ServerClient();
        try {
            serverClient.connect();
            String command = "LOGIN:" + username + ":" + password;
            serverClient.sendCommand(command);
            String[] response = serverClient.receiveResponse().split(":");
            System.out.println("login: " + response);

            if(response[0].equals("FAIL") || response[0].equals("ERROR") || response[0].isBlank() || response[0].isEmpty()) {
                errorLabel.setText("Incorrect login or password");
            } else {
                GlobalState.setUserId(response[1]);
                String role = response[2];
                if(radioButtonClient.isSelected()) {
                    ViewSwitcher.switchScene(event, "client-view", "client-style", 800, 600,this);
                } else if(radioButtonEmployee.isSelected()) {
                    if(role.equals("Customer Service")) {
                        ViewSwitcher.switchScene(event, "costumer-service-representative-view", "employee-style", 800, 600,this);
                    } else if(role.equals("Sorter")) {
                        ViewSwitcher.switchScene(event, "sorter-view", "employee-style", 800, 600,this);
                    } else if(role.equals("Courier")) {
                        ViewSwitcher.switchScene(event, "courier-view", "employee-style", 800, 600,this);
                    } else  {
                        errorLabel.setText("You cannot log in as an employee");
                    }
                } else if(radioButtonAdmin.isSelected()) {
                    if(role.equals("Admin")) {
                        ViewSwitcher.switchScene(event, "admin-view", "admin-style", 1100, 600,this);
                    } else {
                        errorLabel.setText("You cannot log in as an admin");
                    }
                }
            }
        } finally {
            String command = "DISCONNECT";
            serverClient.sendCommand(command);
            serverClient.disconnect();
        }
    }

    public void switchToRegisterScene(ActionEvent event) throws IOException {
        ViewSwitcher.switchScene(event, "register-view", "login-style",800, 800, this);
    }
}
