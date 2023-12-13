package com.example.mailmanagementsystem.controllers;

import com.example.mailmanagementsystem.utils.ViewSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class RegisterController {

    public void backToLogin(ActionEvent event) throws IOException {
        ViewSwitcher.switchScene(event, "login-view", "login-style",800, 500, this);
    }
}
