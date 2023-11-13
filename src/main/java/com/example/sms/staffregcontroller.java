package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class staffregcontroller {

    @FXML
    private AnchorPane container;

    @FXML
    private TextField firstnameinput;

    @FXML
    private Label firstnamemessage;

    @FXML
    private TextField lastnameinput;

    @FXML
    private Label lastnamemessage;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private Label passwordmessage;

    @FXML
    private Button registerbutton;

    @FXML
    private TextField staffidinput;

    @FXML
    private Label staffidmessage;

    @FXML
    private TextField usernameinput;

    @FXML
    private Label usernamemessage;

    @FXML
    private Label staffmessage;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;
    private DatabaseConnection connectSRegister;

    public void signup(ActionEvent event) throws SQLException, IOException {
        String firstname = firstnameinput.getText();
        String lastname = lastnameinput.getText();
        String staffid = staffidinput.getText();
        String username = usernameinput.getText();
        String password = passwordinput.getText();
        if (staffDetailsValidate(firstname, lastname, staffid, username, password) == false) {
            return;
        }
        Staff advisor = new Staff(firstname, lastname, staffid, username, password);
        String insertQuery =
                "INSERT INTO teachers (`Firstname`, `Lastname`, `StaffID`, `Username`, `Password`) VALUES (?, ?, ?, ?, ?)";
        Connection connection = connectSRegister.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, advisor.getFirstname());
            preparedStatement.setString(2, advisor.getLastname());
            preparedStatement.setString(3, advisor.getStaffid());
            preparedStatement.setString(4, advisor.getUsername());
            preparedStatement.setString(5, advisor.getPassword());

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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stafflogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean staffDetailsValidate(String firstName, String lastName, String staffid, String username, String password) {
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
        boolean resultStaffID = staffid.matches("^[a-zA-Z0-9]+$");
        if (resultStaffID == false) {
            staffidmessage.setText("Input only letters and numbers");
            return false;
        }
        staffidmessage.setText(" ");
        boolean resultUsername = username.matches("^[a-zA-Z0-9]+$");
        if (resultUsername == false) {
            usernamemessage.setText("Input only letters and numbers");
            return false;
        }
        usernamemessage.setText(" ");
        for (int i = 0; i < OOPCoursework.advisorList.size(); i++) { //Check if the driver is already there
            if (username.equals(OOPCoursework.advisorList.get(i).username)) {
                usernamemessage.setText("This username already exists");
                return false;
            }
        }
        usernamemessage.setText(" ");
        if (password.length() != 8) {
            passwordmessage.setText("The length of the password should be 8");
            return false;
        }
        passwordmessage.setText(" ");
        String name = firstName + " " + lastName; //Add the firstname and lastname
        for (int i = 0; i < OOPCoursework.advisorList.size(); i++) { //Check if the driver is already there
            if (name.equals(OOPCoursework.advisorList.get(i).firstname + " " + OOPCoursework.advisorList.get(i).lastname)) {
                staffmessage.setText("The student already exists");
                return false;
            }
        }
        staffmessage.setText(" ");
        return true;
    }

}