package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class InchargeclubController  implements Initializable {
    @FXML
    private TableView<Students> studtable;
    @FXML
    private TableColumn<Students, String> usernamecol;
    @FXML
    private TableColumn<Students, String> namecol;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private Label errorLabel;
    private DatabaseConnection connectRegister;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamecol.setCellValueFactory(new PropertyValueFactory<>("username"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        name.appendText(getAdvisorClub().getName());
        description.appendText(getAdvisorClub().getDescription());
        club AdvisorClub = null;
        for (int x = 0; x < OOPCoursework.clublist.size(); x++) {//Finds the advisor's particular club
            if (OOPCoursework.clublist.get(x).getAdvisorID().equals(stafflogincontroller.username1)) {
                AdvisorClub = OOPCoursework.clublist.get(x);
                break;
            }
        }
        for (int z = 0; z < OOPCoursework.studentList.size(); z++) {//Search it with the student list and print the students in a particular club
            if (OOPCoursework.studentList.get(z).getClubs().contains(AdvisorClub)) {
                studtable.getItems().add(OOPCoursework.studentList.get(z));
            }
        }
    }
    private club getAdvisorClub() {//Use to retrieve the relevant club of the advisor
        club advisorClub = null;
        for (club club : OOPCoursework.clublist) {
            if (club.getAdvisorID().equals(stafflogincontroller.username1)) {
                advisorClub = club;
                break;
            }
        }
        return advisorClub;
    }
    @FXML
    public void back(ActionEvent event) throws IOException {//Goes back to the previous FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void remove() throws SQLException {
        Students selectStudent = studtable.getSelectionModel().getSelectedItem();//Gets the  selected  club from the  table
        studtable.getItems().remove(selectStudent);//Removes the above
        club advisorClub = getAdvisorClub();//Stores the advisor club
        int i;
        for (i = 0; i < OOPCoursework.studentList.size(); i++) {
            if (OOPCoursework.studentList.get(i).getUsername().equals(selectStudent.getUsername())) {
                ArrayList<club> studentClubs = selectStudent.getClubs();//Gets the list of clubs which  the particular student registered to
                studentClubs.remove(advisorClub);//Removes the advisor club from the student's list of clubs
                break;
            }
        }
        String insertQuery =
                "UPDATE students SET clubs = ? WHERE Username = ?";
        Connection connection = connectRegister.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {//Updates the clubs column in student's table
            preparedStatement.setString(2, OOPCoursework.studentList.get(i).getUsername());
            preparedStatement.setString(1, OOPCoursework.studentList.get(i).clubString());
            int affectedRow = preparedStatement.executeUpdate();
            if (affectedRow > 0) {
                System.out.println("Updated");
            } else {
                System.out.println("Not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void save() throws SQLException {//updates the edited info.
        String c_name = name.getText().toLowerCase();
        String c_description = description.getText();
        int i;
        for (i = 0; i < OOPCoursework.clublist.size(); i++) {//gets the correct index of the club
            if (OOPCoursework.clublist.get(i).getAdvisorID().equals(stafflogincontroller.username1)) {
                break;
            }
        }
        if (!clubcreation_validation(c_name,c_description)){//validation of the edited inputs
            return;
        }
        String insertQuery =
                "UPDATE clubs SET Name = ?, Description = ?  WHERE AdvisorID = ?";
        Connection connection = connectRegister.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(3, stafflogincontroller.username1);
            preparedStatement.setString(1, OOPCoursework.clublist.get(i).setName(c_name));
            preparedStatement.setString(2, OOPCoursework.clublist.get(i).setDescription(c_description));
            int affectedRow = preparedStatement.executeUpdate();
            if (affectedRow > 0) {
                System.out.println("Updated");
            } else {
                System.out.println("Not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int x;
        for(x=0 ; x<OOPCoursework.studentList.size();x++){// Updates the club column in the student table
            if(OOPCoursework.studentList.get(x).getClubs().contains(getAdvisorClub())){
                break;
            }
        }
        String insertQuery1 =
                "UPDATE students set clubs = ? WHERE Username = ?";
        Connection connection1 = connectRegister.connect();

        try (PreparedStatement preparedStatement = connection1.prepareStatement(insertQuery1)){
            preparedStatement.setString(2,OOPCoursework.studentList.get(x).getUsername());
            preparedStatement.setString(1,OOPCoursework.studentList.get(x).clubString());
            int affectRow1 = preparedStatement.executeUpdate();
            if (affectRow1>0) {
                System.out.println("Updated");
            }else{
                System.out.println("Not Updated");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean clubcreation_validation(String Clubname, String Clubdescrip) {
        boolean ResultClubName = Clubname.matches("[a-zA-Z ]+$");//Checks if the club name contains only letters and stores the result of the checking in a boolean
        boolean ResultDescription = Clubdescrip.matches("[a-zA-Z ]+");//Checks if the club description contains only letters and stores the result of the checking in a boolean
        if (!ResultClubName) {
            errorLabel.setText("Input the details properly!");
            return false;
        }
        errorLabel.setText(" ");
        if (!ResultDescription) {
            errorLabel.setText("Input the details properly!");
            return false;
        }
        errorLabel.setText(" ");
        return true;
    }
}

