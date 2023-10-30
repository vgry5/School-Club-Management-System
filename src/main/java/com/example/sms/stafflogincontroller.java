package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class stafflogincontroller {

    @FXML
    private AnchorPane container;

    @FXML
    private Button enterbutton;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernameinput;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

public void advisorlogin() {
    String username = usernameinput.getText();
    String password = passwordinput.getText();
}

    public void signup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("staffreg.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
