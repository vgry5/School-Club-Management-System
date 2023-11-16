package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class studentdetailscontroller implements Initializable {
    @FXML
    private TextField adnumber;

    @FXML
    private TextField age;

    @FXML
    private AnchorPane container;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDetails();
    }
    public void setDetails() {
        String username = studentlogincontroller.studentLoginDetails.get(0);
        String password = studentlogincontroller.studentLoginDetails.get(1);
        for (int i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).username.equals(username) && OOPCoursework.studentList.get(i).password.equals(password)) {
                firstname.setText(OOPCoursework.studentList.get(i).firstname);
                lastname.setText(OOPCoursework.studentList.get(i).lastname);
                age.setText(String.valueOf(OOPCoursework.studentList.get(i).age));
                adnumber.setText(OOPCoursework.studentList.get(i).admissionNumber);
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
