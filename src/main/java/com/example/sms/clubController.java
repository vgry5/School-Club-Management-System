package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class clubController {
    @FXML
    private Button clubbutton;

    @FXML
    private Button existing;

    @FXML
    private AnchorPane container;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    public void clubs(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("club.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, 744, 689);
        stage.setScene(scene);
        stage.show();
    }
}
