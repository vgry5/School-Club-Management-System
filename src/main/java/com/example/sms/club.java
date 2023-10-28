package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class club {
    private String name;
    private String description;
    private int advisorID;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void clubs(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("club.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, 744, 689);
        stage.setScene(scene);
        stage.show();
    }
    //Constructor
    public club (String name,String description,int advisorID){
        this.name = name;
        this.description = description;
        this.advisorID = advisorID;
    }
    //Getters and Setters(Encapsulation)

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getAdvisorID() {
        return advisorID;
    }
    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }
}