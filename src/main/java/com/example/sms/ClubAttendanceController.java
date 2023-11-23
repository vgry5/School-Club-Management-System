package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClubAttendanceController implements Initializable {

    static String club1;

    @FXML
    private TableView<club> clubtable;

    @FXML
    private AnchorPane container;

    @FXML
    private TableColumn<club, String> namecol;

    @FXML
    private TableColumn<club, Integer> studentscol;

    @FXML
    private Button selectclubbtn;

    @FXML
    private Button backbutton;

    private DatabaseConnection connectSRegister;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namecol.setCellValueFactory(new PropertyValueFactory<club, String>("name"));
        studentscol.setCellValueFactory(new PropertyValueFactory<club, Integer>("no_students"));
        for (int i = 0; i < OOPCoursework.clublist.size(); i++) {
            clubtable.getItems().add(OOPCoursework.clublist.get(i));
        }
    }
    @FXML
    void selectclub(ActionEvent event)throws IOException {
        String selectedClub = clubtable.getSelectionModel().getSelectedItem().getName();
        club1 = selectedClub;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AttendanceMarking.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void back(ActionEvent event)throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
