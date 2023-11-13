package com.example.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OOPCoursework extends Application {
    private DatabaseConnection connect;
    static ArrayList<Students> studentList = new ArrayList<>();
    static ArrayList<Staff> advisorList = new ArrayList<>();
    static ArrayList<Admins> adminList = new ArrayList<>();
    static ArrayList<club>  clublist = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException, SQLException {
//        previousData();
        FXMLLoader fxmlLoader = new FXMLLoader(OOPCoursework.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 744, 689);
        stage.setTitle("CLUB HUB");
        stage.setScene(scene);
        stage.show();
    }

    public void previousData() throws SQLException {
        String selectQuery = "SELECT * FROM `students`;";
        Connection comm = connect.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Students person = new Students(results.getString(1),results.getString(2),results.getInt(3),results.getString(4),results.getString(5),results.getString(6));
                studentList.add(person);
            }
        }
        selectQuery = "SELECT * FROM `teachers`;";
        comm = connect.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Staff advisor = new Staff(results.getString(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5));
                advisorList.add(advisor);
            }
        }
        selectQuery = "SELECT * FROM `admins`;";
        comm = connect.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Admins admin = new Admins(results.getString(1),results.getString(2),results.getString(3),results.getString(4));
                adminList.add(admin);
            }
        }
        selectQuery = "SELECT * FROM `clubs`;";
        comm = connect.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)){
            ResultSet results = statement.executeQuery();
            while (results.next()){
                club Clubs = new club(results.getString(1),results.getString(2),results.getString(3));
                clublist.add(Clubs);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
