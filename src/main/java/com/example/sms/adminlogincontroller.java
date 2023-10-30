package com.example.sms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class adminlogincontroller {

    @FXML
    private AnchorPane container;

    @FXML
    private Button enterbutton;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private Label passwordmessage;

    @FXML
    private TextField usernameinput;

    @FXML
    private Label usernamemessage;


    public void login() {
        String username = usernameinput.getText();
        String password = passwordinput.getText();
    }
}
