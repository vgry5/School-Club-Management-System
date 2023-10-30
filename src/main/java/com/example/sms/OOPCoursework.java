package com.example.sms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class OOPCoursework extends Application {

    static ArrayList<Students> studentList = new ArrayList<>();

    static ArrayList<Staff> advisorList = new ArrayList<>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OOPCoursework.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 744, 689);
        stage.setTitle("CLUB HUB");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}