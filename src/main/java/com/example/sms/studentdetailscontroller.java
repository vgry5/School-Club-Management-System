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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class studentdetailscontroller implements Initializable {
    @FXML
    private TextField adnumberinput;

    @FXML
    private TextField ageinput;

    @FXML
    private AnchorPane container;

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

    @FXML
    private Label agemessage;

    @FXML
    private Label admissionnumbermessage;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    private DatabaseConnection connectSRegister;

    int i;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDetails();
    }
    public void setDetails() {
        String username = studentlogincontroller.studentLoginDetails.get(0);
        String password = studentlogincontroller.studentLoginDetails.get(1);
        for (i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).getUsername().equals(username) && OOPCoursework.studentList.get(i).getPassword().equals(password)) {
                firstnameinput.setText(OOPCoursework.studentList.get(i).getFirstname());
                lastnameinput.setText(OOPCoursework.studentList.get(i).getLastname());
                ageinput.setText(String.valueOf(OOPCoursework.studentList.get(i).getAge()));
                adnumberinput.setText(OOPCoursework.studentList.get(i).getAdmissionNumber());
                usernameinput.setText(OOPCoursework.studentList.get(i).getUsername());
                passwordinput.setText(OOPCoursework.studentList.get(i).getPassword());
                break;
            }
        }
        return;
    }

    public void submit() throws SQLException {
        String firstname = firstnameinput.getText();
        String lastname = lastnameinput.getText();
        String age = ageinput.getText();
        String admissionNumber = adnumberinput.getText();
        String username = usernameinput.getText();
        String password = passwordinput.getText();
        if (!studentDetailsValidate(firstname, lastname, age, admissionNumber, username, password)) {
            return;
        }
        OOPCoursework.studentList.get(i).setFirstname(firstname);
        OOPCoursework.studentList.get(i).setLastname(lastname);
        OOPCoursework.studentList.get(i).setAge(Integer.parseInt(age));
        OOPCoursework.studentList.get(i).setAdmissionNumber(admissionNumber);
        OOPCoursework.studentList.get(i).setUsername(username);
        OOPCoursework.studentList.get(i).setPassword(password);
        String deleteQuery = "DELETE FROM students";
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
                "INSERT INTO students (`Firstname`, `Lastname`, `Age`, `Admission Number`, `Username`, `Password`, `clubs`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        for (int z = 0; z < OOPCoursework.studentList.size(); z++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, OOPCoursework.studentList.get(z).getFirstname());
                preparedStatement.setString(2, OOPCoursework.studentList.get(z).getLastname());
                preparedStatement.setInt(3, OOPCoursework.studentList.get(z).getAge());
                preparedStatement.setString(4, OOPCoursework.studentList.get(z).getAdmissionNumber());
                preparedStatement.setString(5, OOPCoursework.studentList.get(z).getUsername());
                preparedStatement.setString(6, OOPCoursework.studentList.get(z).getPassword());
                preparedStatement.setString(7, OOPCoursework.studentList.get(z).clubString());

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
        if (password.length() != 8) {
            passwordmessage.setText("The length of the password should be 8");
            return false;
        }
        passwordmessage.setText(" ");
        return true;
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
