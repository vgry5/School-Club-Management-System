package com.example.sms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class AdminController implements Initializable {
    @FXML
    private Label warning;
    @FXML
    private TableView<club> adminTable;
    @FXML
    private TableColumn<Admins, String> clubName;
    @FXML
    private TableColumn<Admins, String> adUsername;
    @FXML
    private TableColumn<Admins, String> NoOfStud;
    @FXML
    private ComboBox<String> advisorDrop;
    private DatabaseConnection connectRegister;
    private boolean advisorAddedOrRemoved = false;//To check if advisor added or removed
    ArrayList<String> NotAdvisor_Usernames = new ArrayList<>();//stores advisors without a club
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubName.setCellValueFactory(new PropertyValueFactory<>("name"));
        adUsername.setCellValueFactory(new PropertyValueFactory<>("advisorID"));
        NoOfStud.setCellValueFactory(new PropertyValueFactory<>("no_students"));
        NotAssignedAdvisors();
        ObservableList<String> event_type = advisorDrop.getItems();//add advisors to the comboBox
        int index = 0;
        while (index < NotAdvisor_Usernames.size()) {//adds the usernames to the observable lists
            event_type.add(NotAdvisor_Usernames.get(index));
            index++;
        }
        ObservableList<club> clubData = FXCollections.observableArrayList(OOPCoursework.clublist);//Adds the club details to the table
        adminTable.setItems(clubData);
    }
    private void NotAssignedAdvisors() {
        for (int x = 0; x < OOPCoursework.advisorList.size(); x++) {
            boolean isAssigned = false;//used to check is an advisor is assigned to a club
            Staff currentAdvisor = OOPCoursework.advisorList.get(x);
            for (int z = 0; z < OOPCoursework.clublist.size(); z++) {
                club currentClub = OOPCoursework.clublist.get(z);
                if (currentAdvisor.getUsername().equals(currentClub.getAdvisorID())) {//checks if an advisor is assigned to a club
                    isAssigned = true;//if yes the boolean variable becomes true
                    break;//to stop checking
                }
            }
            if (!isAssigned) {//identifies the advisors with no clubs
                NotAdvisor_Usernames.add(currentAdvisor.getUsername());//adds to the array list
            }
        }
    }
    @FXML
    void remove() throws SQLException {
            club selectedClub = adminTable.getSelectionModel().getSelectedItem();
            if (selectedClub != null) {
                String advisorIDToRemove = selectedClub.getAdvisorID();//Gets the username of the selected advisor
                for (int i = 0; i < OOPCoursework.clublist.size(); i++) {//Removes the advisor from the club
                    club c = OOPCoursework.clublist.get(i);
                    if (c.getAdvisorID() != null && c.getAdvisorID().equals(advisorIDToRemove)) {
                        c.setAdvisorID(null);
                    }
                }
                boolean advisorAssigned = false;//checks if advisor is assigned to any club after the removal
                for (int i = 0; i < OOPCoursework.clublist.size(); i++) {
                    club c = OOPCoursework.clublist.get(i);
                    if (c.getAdvisorID() != null && c.getAdvisorID().equals(advisorIDToRemove)) {
                        advisorAssigned = true;
                        break;
                    }
                }
                if (!advisorAssigned) {//if not assigned to any club , remove the advisor
                    for (int i = 0; i < OOPCoursework.advisorList.size(); i++) {
                        Staff advisor = OOPCoursework.advisorList.get(i);
                        if (advisor.getUsername().equals(advisorIDToRemove)) {
                            OOPCoursework.advisorList.remove(i);
                            break;
                        }
                    }
                }
                advisorAddedOrRemoved = true;//marks the advisor as removed
                adminTable.refresh();//refresh
                String updateQuery = "DELETE FROM `teachers` WHERE Username = ?";
                Connection connection = connectRegister.connect();
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, advisorIDToRemove);
                    int affectedRow = preparedStatement.executeUpdate();
                    if (affectedRow > 0) {
                        System.out.println("Advisor ID removed from clubs table");
                    } else {
                        System.out.println("No rows affected or Advisor ID not found in clubs table");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        @FXML
        void add() throws SQLException {
            String selectedAdvisor = advisorDrop.getValue();//Gets value from drop down
            club selectedClub = adminTable.getSelectionModel().getSelectedItem();
            if (selectedAdvisor != null && selectedClub != null) {
                selectedClub.setAdvisorID(selectedAdvisor);
                for (int i = 0; i < OOPCoursework.clublist.size(); i++) {//updates the new advisor to the clublist arraylist
                    club c = OOPCoursework.clublist.get(i);
                    if (c.getName().equals(selectedClub.getName())) {
                        c.setAdvisorID(selectedAdvisor);
                        break;
                    }
                }
                adminTable.refresh();
            }
        String updateQuery = "UPDATE clubs SET AdvisorID = ? WHERE Name = ?";
        Connection connection = connectRegister.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, selectedAdvisor);
            preparedStatement.setString(2, selectedClub.getName());
            int affectedRow = preparedStatement.executeUpdate();
            if (affectedRow > 0) {
                System.out.println("Club table updated with the new advisor");
                adminTable.refresh();
            } else {
                System.out.println("No rows affected or Club not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            advisorAddedOrRemoved = true;
            updateQuery = "UPDATE teachers SET clubs = ? WHERE Username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, selectedClub.getName());
                preparedStatement.setString(2, selectedAdvisor);
                int affectedRow = preparedStatement.executeUpdate();
                if (affectedRow > 0) {
                    System.out.println("Club table updated with the new advisor");
                    adminTable.refresh();
                } else {
                    System.out.println("No rows affected or Club not found");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            advisorAddedOrRemoved = true;
        }
    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainmenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 }
