package com.example.sms;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class clubCreationController {

    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private Label namelabel;
    @FXML
    private Label descriplabel;
    @FXML
    private Label moreclub;
    private  DatabaseConnection connectclubcreation;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static ArrayList<String> ClubAd_Username = new ArrayList<>();

    public clubCreationController() {
    }
    public void clubCreation (ActionEvent event) throws IOException,SQLException {
        String Clubname = name.getText().toUpperCase();
        String Clubdescrip = description.getText();
        String advisorID = stafflogincontroller.username1;
        System.out.println(advisorID);
        for (int z=0; z<OOPCoursework.clublist.size();z++){//Adds all the existing usernames with clubs to a arraylist
            ClubAd_Username.add(OOPCoursework.clublist.get(z).getAdvisorID());}
        if(ClubAd_Username.contains(advisorID)){//checks if the advisor has already made a club
            moreclub.setText("Cannot create more than one club!");
            PauseTransition pauseTransition = getPauseTransition(event);//The transition pause
            pauseTransition.play();
        }
        else{int no_students = 0;//if the advisor hasn't made a club before, allows him/her to create a club
        if (!clubcreation_validation(Clubname, Clubdescrip)){//validation of the inputs
            return;
        }
        club Clubs = new club(Clubname,Clubdescrip,advisorID,no_students);
        OOPCoursework.clublist.add(Clubs);
        String insertQuery =
                "INSERT INTO clubs(`Name` , `AdvisorID`, `Description`, `No_Students`)VALUES(?, ?, ?, ?)";
        Connection connection = connectclubcreation.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, Clubs.getName());
            preparedStatement.setString(2, Clubs.getAdvisorID());
            preparedStatement.setString(3, Clubs.getDescription());
            preparedStatement.setInt(4,Clubs.getNo_students());
            int rowInsert = preparedStatement.executeUpdate();
            if (rowInsert > 0) {
                System.out.println("Data inserted successfully");
            } else {
                System.out.println("Data insertion failed");
            }
        }catch (SQLException e){
            System.out.println();
        }}
    }
    private PauseTransition getPauseTransition(ActionEvent event) {//To pause the transition for a while
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
        pauseTransition.setOnFinished(event1 -> {
            try {root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();}catch (IOException e){
                e.printStackTrace();
            }
        });
        return pauseTransition;
    }
    public boolean clubcreation_validation(String Clubname, String Clubdescrip) {
        boolean ResultClubName = Clubname.matches("[a-zA-Z ]+$");//Checks if the club name contains only letters and stores the result of the checking in a boolean
        boolean ResultDescription = Clubdescrip.matches("[a-zA-Z ]+");//Checks if the club description contains only letters and stores the result of the checking in a boolean
        for(int x=0;x<OOPCoursework.clublist.size();x++){
            if(OOPCoursework.clublist.get(x).getName().equals(Clubname)){
                    namelabel.setText("This club already exists");
                    return false;
            }
        }
        namelabel.setText(" ");
        if (!ResultClubName) {
            namelabel.setText("Input a proper club name!");
            return false;
        }
        namelabel.setText(" ");
        if (!ResultDescription) {
            descriplabel.setText("Input the description properly!");
            return false;
        }
        descriplabel.setText(" ");
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

