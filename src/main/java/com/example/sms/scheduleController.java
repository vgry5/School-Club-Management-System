package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class scheduleController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField EventName;
    @FXML
    private DatePicker date;
//    @FXML
//    private TextField location;
//

    public void eventSchedule(ActionEvent event ) throws IOException{





    }
    public void calender (ActionEvent actionEvent) throws IOException {   // going to calender view

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calender.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, 744, 689);
        stage.setScene(scene);
        stage.show();
    }
}
