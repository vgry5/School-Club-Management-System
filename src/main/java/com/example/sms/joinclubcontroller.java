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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private DatabaseConnection connectSRegister;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set values of column attributes to the students class
        namecol.setCellValueFactory(new PropertyValueFactory<club, String>("name"));
        advisorcol.setCellValueFactory(new PropertyValueFactory<club, String>("advisorID"));
        studentscol.setCellValueFactory(new PropertyValueFactory<club, Integer>("no_students"));
        //Add students to the table from students list
        for (int i = 0; i < OOPCoursework.clublist.size(); i++) {
            clubtable.getItems().add(OOPCoursework.clublist.get(i));
        }
    }

    @FXML
    public void joinClub() throws SQLException {
        //Check if the student list is zero
        if (OOPCoursework.studentList.size() == 0) {
            return;
        }
        //Get the login details
        String username = studentlogincontroller.studentLoginDetails.get(0);
        String password = studentlogincontroller.studentLoginDetails.get(1);
        //Get the selected student from the table and check if the student has already joined the club
        int selectedClub = clubtable.getSelectionModel().getSelectedIndex();
        int i = 0;
        for (i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).getUsername().equals(username) && OOPCoursework.studentList.get(i).getPassword().equals(password)) {
                for(int z = 0 ; z < OOPCoursework.studentList.get(i).clubs.size() ; z++) {
                    if (OOPCoursework.clublist.get(selectedClub).getName().equals(OOPCoursework.studentList.get(i).clubs.get(z).getName())) {
                        return;
                    }
                }
            }
        }
        //Get the club object
        for (i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).getUsername().equals(username) && OOPCoursework.studentList.get(i).getPassword().equals(password)) {
                OOPCoursework.studentList.get(i).addClub(OOPCoursework.clublist.get(selectedClub));
                OOPCoursework.clublist.get(selectedClub).addStudent();
                break;
            }
        }
        //Update student number in the clubs table
        String insertQuery =
                "UPDATE clubs SET No_Students = ? WHERE Name = ?";
        Connection connection = connectSRegister.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(2, OOPCoursework.clublist.get(selectedClub).getName());
            preparedStatement.setString(1, String.valueOf(OOPCoursework.clublist.get(selectedClub).getNo_students()));

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check the number of rows affected
            if (rowsAffected > 0) {
                System.out.println("Club updated successfully for student with username: " + username);
            } else {
                System.out.println("No rows were updated. Student with username " + username + " not found.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        //Update student clubs in the student table
        insertQuery =
                "UPDATE students SET clubs = ? WHERE Username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(2, username);
            preparedStatement.setString(1, OOPCoursework.studentList.get(i).clubString());

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check the number of rows affected
            if (rowsAffected > 0) {
                System.out.println("Club updated successfully for student with username: " + username);
            } else {
                System.out.println("No rows were updated. Student with username " + username + " not found.");
            }
        }
        //Exception handling to check sql
     catch (SQLException e) {
        e.printStackTrace();
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

