package com.example.sms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class studentlogincontroller {

    @FXML
    private AnchorPane container;

    @FXML
    private Button enterbutton;

    @FXML
    private PasswordField passwordinput;

    @FXML
    private Label passwordmessage;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernameinput;

    @FXML
    private Label usernamemessage;


    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    private DatabaseConnection connect;
    public void  studentlogincontroller(){this.connect = new DatabaseConnection(); }
    public void login(ActionEvent event) throws IOException, SQLException {
        String username = usernameinput.getText();
        String password = passwordinput.getText();


        String selectQuery = "SELECT * FROM `students`;";
        Connection comm= connect.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                System.out.println(results.getString(5));
                if (username.equals(results.getString(5)) && password.equals(results.getString(6))) {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("schedule .fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void signup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentreg.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}