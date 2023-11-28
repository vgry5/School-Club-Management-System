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
    static ArrayList<event>scheduleEvents= new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        previousData();
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
                Admins admin = new Admins(results.getString(1),results.getString(2),
                        results.getString(3),results.getString(4));
                adminList.add(admin);
            }
        }
        selectQuery = "SELECT * FROM `clubs`;";
        comm = connect.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)){
            ResultSet results = statement.executeQuery();
            while (results.next()){
                club Clubs = new club(results.getString(1),results.getString(3),
                        results.getString(2),results.getInt(4));
                clublist.add(Clubs);
            }
        }
        selectQuery = "SELECT " + "clubs" + " FROM students";
        int i = 0;
        try (PreparedStatement preparedStatement = comm.prepareStatement(selectQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Loop through the result set
                while (resultSet.next()) {
                    String columnValue = resultSet.getString("clubs");
                    if(columnValue == null) {
                        i++;
                        continue;
                    }
                    String[] clubs = columnValue.split(",");
                    for (String club : clubs) {
                        for(int z = 0 ; z < OOPCoursework.clublist.size() ; z++) {
                            if (club.equals(OOPCoursework.clublist.get(z).getName())) {
                                OOPCoursework.studentList.get(i).addClub(OOPCoursework.clublist.get(z));
                            }
                        }
                    }
                    i++;
                    }
                }
            }


         selectQuery = "SELECT * FROM `events`;";
         comm = connect.connect();
            try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
                ResultSet results = statement.executeQuery();
                while (results.next()) {
                    event scheduledEvents = new event(results.getString(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5));
                    scheduleEvents.add(scheduledEvents);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public static void main(String[] args) {
        launch();
    }
}
