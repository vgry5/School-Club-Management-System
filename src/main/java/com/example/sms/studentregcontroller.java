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

public class studentregcontroller {

    @FXML
    private Label admissionnumbermessage;

    @FXML
    private TextField adnumberinput;

    @FXML
    private TextField ageinput;

    @FXML
    private Label agemessage;

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
    private Label studentmessage;

    @FXML
    private TextField usernameinput;

    @FXML
    private Label usernamemessage;
    private DatabaseConnection connectSRegister;
    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    public void signup(ActionEvent event) throws SQLException, IOException {
        String firstname = firstnameinput.getText();
        String lastname = lastnameinput.getText();
        String age = ageinput.getText();
        String admissionNumber = adnumberinput.getText();
        String username = usernameinput.getText();
        String password = passwordinput.getText();
        if (!studentDetailsValidate(firstname, lastname, age, admissionNumber, username, password)) {
            return;
        }
        Students student = new Students(firstname, lastname, Integer.parseInt(age),admissionNumber, username, password);
        String insertQuery =
                "INSERT INTO students (`Firstname`, `Lastname`, `Age`, `Admission Number`, `Username`, `Password`) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = connectSRegister.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getAdmissionNumber());
            preparedStatement.setString(5, student.getUsername());
            preparedStatement.setString(6, student.getPassword());

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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentlogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

        public boolean studentDetailsValidate(String firstName, String lastName, String inputAge, String inputAdmissionNumber, String username, String password) {
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
        int age;
        try {    //Exception handling to store age in a variable
            age = Integer.parseInt(inputAge);
        } catch (NumberFormatException e) {
            agemessage.setText("Input only numbers");
            return false;
        }
        agemessage.setText(" ");
        if (age < 5 || age > 20) {
            agemessage.setText("Enter a age between 5 and 20");
            return false;
        }
        agemessage.setText(" ");
        int admissionNumber;
        try {    //Exception handling to store age in a variable
            admissionNumber = Integer.parseInt(inputAdmissionNumber);
        } catch (NumberFormatException e) {
            admissionnumbermessage.setText("Invalid admission number");
            return false;
        }
        admissionnumbermessage.setText(" ");
        boolean resultUsername = username.matches("^[a-zA-Z0-9]+$");
        if (resultUsername == false) {
            usernamemessage.setText("Input only letters and numbers");
            return false;
        }
        usernamemessage.setText(" ");
        for (int i = 0; i < OOPCoursework.studentList.size(); i++) { //Check if the driver is already there
            if (username.equals(OOPCoursework.studentList.get(i).username)) {
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
        for (int i = 0; i < OOPCoursework.studentList.size(); i++) { //Check if the driver is already there
            if (name.equals(OOPCoursework.studentList.get(i).firstname + " " + OOPCoursework.studentList.get(i).lastname)) {
                studentmessage.setText("The student already exists");
                return false;
            }
        }
        studentmessage.setText(" ");
        return true;
    }

    @FXML
    void back(ActionEvent event)throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentlogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}