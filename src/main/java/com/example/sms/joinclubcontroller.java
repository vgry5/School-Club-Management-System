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

public class joinclubcontroller implements Initializable {

    @FXML
    private TableColumn<club, String> advisorcol;

    @FXML
    private TableView<club> clubtable;

    @FXML
    private AnchorPane container;

    @FXML
    private Button joinbutton;

    @FXML
    private TableColumn<club, String> namecol;

    @FXML
    private TableColumn<club, Integer> studentscol;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set values of column attributes to the drivers class
        namecol.setCellValueFactory(new PropertyValueFactory<club, String>("name"));
        advisorcol.setCellValueFactory(new PropertyValueFactory<club, String>("advisorID"));
        studentscol.setCellValueFactory(new PropertyValueFactory<club, Integer>("no_students"));
        //Add drivers to the table from drivers list
        for (int i = 0; i < OOPCoursework.clublist.size(); i++) {
            clubtable.getItems().add(OOPCoursework.clublist.get(i));
        }
    }

    @FXML
    public void joinClub() {
        if (OOPCoursework.studentList.size() == 0) {
            return;
        }
        String username = studentlogincontroller.studentLoginDetails.get(0);
        String password = studentlogincontroller.studentLoginDetails.get(1);
        for (int i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).username.equals(username) && OOPCoursework.studentList.get(i).password.equals(password)) {
                int selectedClub = clubtable.getSelectionModel().getSelectedIndex();
                OOPCoursework.studentList.get(i).addClub(OOPCoursework.clublist.get(selectedClub)); //selectedDriver saves the index of the driver from the table and used to delete the driver
            }
        }
        return;

    }

    @FXML
    void back(ActionEvent event)throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentmenu.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

