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

public class studentclubscontroller implements Initializable {

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

        //Set values of column attributes to the student class
        namecol.setCellValueFactory(new PropertyValueFactory<club, String>("name"));
        advisorcol.setCellValueFactory(new PropertyValueFactory<club, String>("advisorID"));
        studentscol.setCellValueFactory(new PropertyValueFactory<club, Integer>("no_students"));
        //Add clubs to the table from student
        String username = studentlogincontroller.studentLoginDetails.get(0);
        String password = studentlogincontroller.studentLoginDetails.get(1);
        for (int i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).getUsername().equals(username) && OOPCoursework.studentList.get(i).getPassword().equals(password)) {
                for (int z = 0; z < OOPCoursework.studentList.get(i).clubs.size(); z++) {
                    clubtable.getItems().add(OOPCoursework.studentList.get(i).clubs.get(z));
                }
            }
        }
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
