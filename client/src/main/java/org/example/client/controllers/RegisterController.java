package org.example.client.controllers;

import javafx.event.ActionEvent;
import org.example.client.utils.ViewSwitcher;

import java.io.IOException;

public class RegisterController {

    public void backToLogin(ActionEvent event) throws IOException {
        ViewSwitcher.switchScene(event, "login-view", "login-style",800, 500, this);
    }
}
