package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class schedule {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void calender (ActionEvent actionEvent) throws IOException {   // going to calender view

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calender.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, 744, 689);
        stage.setScene(scene);
        stage.show();
    }
}
