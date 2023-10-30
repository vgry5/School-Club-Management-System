package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class clubCreationController {
    @FXML
    private AnchorPane container;
    @FXML
    private Button create;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField adid;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void clubcreation(){
        String Clubname = name.getText();
        String Clubdescrip = description.getText();
        String advisorID = adid.getText();

    }
    private void clubCreation (ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clubcreation.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
