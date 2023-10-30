package com.example.sms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class studentregcontroller {

    @FXML
    private TextField adnumberinput;

    @FXML
    private TextField ageinput;

    @FXML
    private AnchorPane container;

    @FXML
    private TextField firstnameinput;

    @FXML
    private TextField lastnameinput;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private Button registerbutton;

    @FXML
    private TextField usernameinput;


    public void signup() {
        String firstname = firstnameinput.getText();
        String lastname = lastnameinput.getText();
        int age = Integer.parseInt(ageinput.getText());
        String username = usernameinput.getText();
        String password = passwordinput.getText();
        Students student = new Students(firstname, lastname, age, username, password);
    }
}