package com.example.sms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportGenerationController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label reportMessage;
    @FXML
    private ComboBox<String> reportSelect;

    @FXML
    private TableColumn<Staff,String> AdviserName;

    @FXML
    private TableView<String> EventAttendence;

    @FXML
    private TableColumn<?, ?> NoofStudent;

    @FXML
    private Button backbutton;

    @FXML
    private TableView<?> clubActivities;

    @FXML
    private TableView<?> clubMembership;

    @FXML
    private TableColumn<?, ?> clubName;



    @FXML
    private TableColumn<?, ?> eventName;

    @FXML
    private Button generateButton;

    @FXML
    private TableColumn<?, ?> noOfStudent;

    @FXML
    void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void generate (ActionEvent event)throws IOException {
        String report = reportSelect.getValue();
        if (report!= null){
            if (report.equals("club membership")){
                ClubMembership();
            } else if (report.equals("event attendance")) {
                eventAttendence();
            } else if (report.equals("club activities")) {
                clubActivities();
            }
        }else {
           reportMessage.setText("Select a report Type");
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> report = FXCollections.observableArrayList("club membership","event attendance", "club activities");
        reportSelect.setItems(report);
    }
    private void ClubMembership(){
        clubMembership.toFront();
    }
    private void eventAttendence(){
        EventAttendence.toFront();
    }
    private void clubActivities(){
        clubActivities.toFront();
    }
}
