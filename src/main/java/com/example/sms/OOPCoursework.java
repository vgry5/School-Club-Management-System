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

    public void studentlogincontroller() {
        this.connect = new DatabaseConnection();
    }


    static ArrayList<Students> studentList = new ArrayList<>();

    static ArrayList<Staff> advisorList = new ArrayList<>();

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
                Students  person = new Students(results.getString(1),results.getString(2),results.getInt(3),results.getString(4),results.getString(5),results.getString(6));
                studentList.add(person);

            }
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
