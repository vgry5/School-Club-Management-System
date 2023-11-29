package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdvisorProfileController implements Initializable {
    @FXML
    private TextField staffID;

    @FXML
    private TextField firstnameinput;

    @FXML
    private TextField lastnameinput;

    @FXML
    private TextField usernameinput;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private Label firstnamemessage;

    @FXML
    private Label lastnamemessage;

    @FXML
    private Label passwordmessage;

    @FXML
    private Label studentmessage;

    @FXML
    private Label usernamemessage;


    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;
    private DatabaseConnection connectSRegister;
    int x;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {setAdvisorDetails();}
    public void setAdvisorDetails(){
        String username = stafflogincontroller.staffLoginDetails.get(0);
        String password = stafflogincontroller.staffLoginDetails.get(1);
        for (x=0;x<OOPCoursework.advisorList.size();x++){
            if(OOPCoursework.advisorList.get(x).getUsername().equals(username) && OOPCoursework.advisorList.get(x).getPassword().equals(password)){
                firstnameinput.setText(OOPCoursework.advisorList.get(x).getFirstname());
                lastnameinput.setText(OOPCoursework.advisorList.get(x).getLastname());
                staffID.setText(OOPCoursework.advisorList.get(x).getStaffid());
                usernameinput.setText(OOPCoursework.advisorList.get(x).getUsername());
                passwordinput.setText(OOPCoursework.advisorList.get(x).getPassword());
                break;
            }
        }
    }
    public void sumbit() throws SQLException{
        String firstname = firstnameinput.getText();
        String lastname = lastnameinput.getText();
        String StaffID= staffID.getText();
        String username = usernameinput.getText();
        String password = passwordinput.getText();
        if (!staffDetailsValidate(firstname, lastname,username, password)) {
            return;
        }
        OOPCoursework.advisorList.get(x).setFirstname(firstname);
        OOPCoursework.advisorList.get(x).setLastname(lastname);
        OOPCoursework.advisorList.get(x).setStaffid(StaffID);
        OOPCoursework.advisorList.get(x).setUsername(username);
        OOPCoursework.advisorList.get(x).setPassword(password);
        String deleteQuery = "DELETE FROM teachers";
        Connection connection = connectSRegister.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            // Execute the SQL DELETE statement
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("All rows deleted from the table successfully!");
            } else {
                System.out.println("No rows deleted from the table.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String insertQuery =
                "INSERT INTO teachers (`Firstname`, `Lastname`, `StaffID`, `Username`, `Password`) VALUES (?, ?, ?, ?, ?)";
        for (int z = 0; z < OOPCoursework.advisorList.size(); z++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, OOPCoursework.advisorList.get(z).getFirstname());
                preparedStatement.setString(2, OOPCoursework.advisorList.get(z).getLastname());
                preparedStatement.setString(3, OOPCoursework.advisorList.get(z).getStaffid());
                preparedStatement.setString(4, OOPCoursework.advisorList.get(z).getUsername());
                preparedStatement.setString(5, OOPCoursework.advisorList.get(z).getPassword());
                // Execute the SQL INSERT statement
                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Data insertion failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean staffDetailsValidate(String firstName, String lastName, String username, String password) {
        boolean resultFirstname = firstName.matches("[a-zA-Z]+");  //Check if firstname contains only letter and store the result in a boolean
        boolean resultLastname = lastName.matches("[a-zA-Z]+");    //Check if firstname contains only letter and store the result in a boolean
        if (resultFirstname == false) {
            firstnamemessage.setText("Input only letters");
            return false;
        }
        firstnamemessage.setText(" ");
        if (resultLastname == false) {
            lastnamemessage.setText("Input only letters");
            return false;
        }
        lastnamemessage.setText(" ");

        boolean resultUsername = username.matches("^[a-zA-Z0-9]+$");
        if (resultUsername == false) {
            usernamemessage.setText("Input only letters and numbers");
            return false;
        }
        usernamemessage.setText(" ");
        if (password.length() != 8) {
            passwordmessage.setText("The length of the password should be 8");
            return false;
        }
        passwordmessage.setText(" ");
        return true;
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
