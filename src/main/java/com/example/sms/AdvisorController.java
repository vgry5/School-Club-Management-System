package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
public class AdvisorController {
    @FXML
    private  Button advisorbutton;
    @FXML
    private Button joinbutton;
    @FXML
    private Button viewbutton;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void joinbutton (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clubcreation.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void advisorbutton(ActionEvent actionEvent){
    }
    public void viewbutton (ActionEvent actionEvent){

    }
    public void back(ActionEvent actionEvent) {
    }
}
