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

public class leaveclubcontroller implements Initializable {

    @FXML
    private TableColumn<club, String> advisorcol;

    @FXML
    private TableView<club> clubtable;

    @FXML
    private AnchorPane container;

    @FXML
    private Button leavebutton;

    @FXML
    private TableColumn<club, String> namecol;

    @FXML
    private TableColumn<club, Integer> studentscol;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    private DatabaseConnection connectSRegister;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set values of column attributes to the drivers class
        namecol.setCellValueFactory(new PropertyValueFactory<club, String>("name"));
        advisorcol.setCellValueFactory(new PropertyValueFactory<club, String>("advisorID"));
        studentscol.setCellValueFactory(new PropertyValueFactory<club, Integer>("no_students"));
        //Add drivers to the table from drivers list
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
    void leave(ActionEvent event) throws IOException, SQLException {
        int selectedClub = clubtable.getSelectionModel().getSelectedIndex();
        clubtable.getItems().remove(selectedClub); //selectedDriver saves the index of the driver from the table and used to delete the driver
        String username = studentlogincontroller.studentLoginDetails.get(0);
        String password = studentlogincontroller.studentLoginDetails.get(1);
        int i;
        for (i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).getUsername().equals(username) && OOPCoursework.studentList.get(i).getPassword().equals(password)) {
                club club = OOPCoursework.studentList.get(i).removeClub(selectedClub);
                club.removeStudent();
                break;
            }
        }
        String insertQuery =
                "UPDATE students SET clubs = ? WHERE Username = ?";
        Connection connection = connectSRegister.connect();

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
        catch (SQLException e) {
            e.printStackTrace();
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
