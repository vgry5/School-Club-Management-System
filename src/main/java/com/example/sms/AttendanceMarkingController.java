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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AttendanceMarkingController implements Initializable {

    ArrayList<String> eventslist = new ArrayList<>();


    @FXML
    private Button absentbtn;

    @FXML
    private TableColumn<String, String> attendance;



    @FXML
    private Button presentbtn;

    @FXML
    private ComboBox<String> selecteventdropdown;

    @FXML
    private TableView<String> stdNameTbl;

    @FXML
    private TableColumn<Students, String> studentname;

    @FXML
    private Button backbutton;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;
    private DatabaseConnection connectEvent;

    String clubName = ClubAttendanceController.club1; //getting the selected club for the table in club attendance.

    ArrayList<String> displayStudent = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> events = selecteventdropdown.getItems();
        int index = 0;
        while (index < eventslist.size()) {
            events.add(eventslist.get(index));
            index++;
        }
    }
    private void allEvents() throws SQLException {
        String selectQuery = "SELECT * FROM `events`;";
        Connection comm = connectEvent.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                if (clubName.equals(results.getString(2))){
                    eventslist.add(results.getString(1));
                }
            }
        }
        try {
            clubMembers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> ViewStudents = FXCollections.observableArrayList(displayStudent);
//        studentname.setCellValueFactory(new PropertyValueFactory<Students,String>("firstname"));
        //attendance.setCellValueFactory(new PropertyValueFactory<String,String>("attendance"));
//        stdNameTbl.setItems(ViewStudents);
    }
    @FXML
    void back(ActionEvent event)throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ClubAttendance.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void clubMembers() throws SQLException {
        String selectQuery = "SELECT * FROM `students`;";
        Connection comm = connectEvent.connect();
        int i = 0;
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                // Loop through the result set
                while (resultSet.next()) {
                    String columnValue = resultSet.getString("clubs");
                    if (columnValue == null) {
                        i++;
                        continue;
                    }
                    String[] clubs = columnValue.split(",");
                    List<String> arrayList = new ArrayList<>(Arrays.asList(clubs));
                    if (arrayList.contains(ClubAttendanceController.club1)) {
                        displayStudent.add(resultSet.getString(1));
                    }
                }
            }
        }
    }
    }