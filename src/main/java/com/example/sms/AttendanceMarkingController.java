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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AttendanceMarkingController implements Initializable {

    ArrayList<String> eventslist = new ArrayList<>(); // Creating an arraylist to store the events

    @FXML
    private Button absentbtn;

    @FXML
    private TableColumn<Attendance, String> attendance;

    @FXML
    private Button presentbtn;

    @FXML
    private ComboBox<String> selecteventdropdown;

    @FXML
    private TableView<Attendance> stdNameTbl;

    @FXML
    private TableColumn<Attendance, String> studentname;

    @FXML
    private Button backbutton;

    @FXML
    private Button submitattendance;

    @FXML
    private Button viewbtn;

    @FXML
    private Label selecteventmsg;

    @FXML
    private Label nostudentmsg;

    private Stage stage; //creating variables for scene, stage and root
    private Scene scene;
    private Parent root;
    private DatabaseConnection connectEvent; // to connect the database
    private Set<String> uniqueStudentNames = new HashSet<>();

   String clubName; //getting the selected club for the table in club attendance.
    Attendance selectedAttendance;

    ObservableList<Attendance> displayStudent = FXCollections.observableArrayList(); //creating observable lists for table view
    ObservableList<Attendance> markAttendance = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> events = selecteventdropdown.getItems();  // to get the events from the dropdown list
        int index = 0;
        while (index < eventslist.size()) {
            events.add(eventslist.get(index));
            index = index+2;
        }
    }

    private void allEvents() throws SQLException {
        String selectQuery = "SELECT * FROM `events`;";  //to load all the events related to the club.
        Connection comm = connectEvent.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                for (int i = 0; i < OOPCoursework.clublist.size(); i++) {
                    if (stafflogincontroller.username1.equals(OOPCoursework.clublist.get(i).getAdvisorID())){
                        clubName = OOPCoursework.clublist.get(i).getName();
                        if (clubName.equals(results.getString(2))){
                            eventslist.add(results.getString(1));
                            eventslist.add(results.getString(4));
                        }
                    }
                }
            }
        }

    }
    @FXML
    void back(ActionEvent event)throws IOException {// to go back
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    String name;
    String blank;

    public void clubMembers() throws SQLException { // showing the members of the club
        attendenceDatabse();
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
                    if (arrayList.contains(clubName)){
                        name = resultSet.getString(1);
                        blank = " ";

                        // Check if the student is already in the displayStudent list
                        boolean studentFound = false;
                        for (Attendance attendance : displayStudent) {
                            if (attendance.getUsername1().equals(name)) {
                                studentFound = true;
                            }
                        }
                        // If the student is not found in the list, add a new row
                        if (!studentFound) {
                            displayStudent.add(new Attendance(name, blank));
                        }
                    }
                }
            }
        }
    }

   @FXML
    void markPresent(ActionEvent event) throws IOException {
        selectedAttendance = stdNameTbl.getSelectionModel().getSelectedItem();
        if (selectedAttendance == null) {
            return;
        }
        // To Update the status to Present
        selectedAttendance.setAttendence("Present");


        stdNameTbl.refresh();  // To Refresh the TableView
    }
    @FXML
    void markAbsent(ActionEvent event) throws IOException {
        selectedAttendance = stdNameTbl.getSelectionModel().getSelectedItem();
        if (selectedAttendance == null) {
            return;
        }
        // Update the status to "Present"
        selectedAttendance.setAttendence("Absent");

        stdNameTbl.refresh();
   }
    String eventName;

@FXML
public String submitattendance(ActionEvent event) throws IOException, SQLException { //saving the data to database
    if (stdNameTbl.getSelectionModel().getSelectedItem() == null ||
            stdNameTbl.getSelectionModel().getSelectedItem().getUsername1() == null) {
        nostudentmsg.setText("Please Select A Student!");
        return null;
    }

    nostudentmsg.setText(" ");

    eventName = selecteventdropdown.getValue();
    String club = clubName;
    String date = null;
//
    for (int i = 0; i < eventslist.size(); i++) {
        if (eventName.equals(eventslist.get(i))) {
            date = eventslist.get(i + 1);
        }
    }

    String studentName = stdNameTbl.getSelectionModel().getSelectedItem().getUsername1();
    String attendance = selectedAttendance != null ? selectedAttendance.getAttendence() : null;

    if (attendance == null || attendance.isEmpty()) {
        nostudentmsg.setText("Please Mark the Attendance of Students!");
        return null;
    }

    // To Check if the record already exists in the database
    boolean recordExists = checkAttendanceRecord(eventName, club, date, studentName);

    if (recordExists) {
        // To Update the existing record
        updateAttendance(eventName, club, date, studentName, attendance);
    } else {
        // To Insert a new record
        insertAttendance(eventName, club, date, studentName, attendance);
    }


    return eventName;
}

    private boolean checkAttendanceRecord(String eventName, String club, String date, String studentName) throws SQLException {
        String selectQuery = "SELECT * FROM `attendanc` WHERE `EventName`=? AND `ClubName`=? AND `Date`=? AND `Student Name`=?";
        Connection connection = connectEvent.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, club);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, studentName);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Return true if a record exists
        }
    }

    private void updateAttendance(String eventName, String club, String date, String studentName, String attendance) throws SQLException {//updating the data if the student is added previously
        String updateQuery = "UPDATE `attendanc` SET `Attendance`=? WHERE `EventName`=? AND `ClubName`=? AND `Date`=? AND `Student Name`=?";
        Connection connection = connectEvent.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, attendance);
            preparedStatement.setString(2, eventName);
            preparedStatement.setString(3, club);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, studentName);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Data update failed.");
            }
        }
    }

    private void insertAttendance(String eventName, String club, String date, String studentName, String attendance) throws SQLException {// adding the new data
        String insertQuery = "INSERT INTO attendanc(`EventName`, `ClubName`, `Date`, `Student Name`, `Attendance`) VALUES (?,?,?,?,?)";
        Connection connection = connectEvent.connect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, eventName);
            preparedStatement.setString(2, club);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, studentName);
            preparedStatement.setString(5, attendance);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data insertion failed.");
            }
        }
    }

    private void attendenceDatabse ()throws SQLException {  //loading the previous attendence data
        eventName = selecteventdropdown.getValue();
        String selectQuery = "SELECT * FROM `attendanc`;";
        Connection comm = connectEvent.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                if (eventName.equals(results.getString(1))){
                    displayStudent.addAll(new Attendance(results.getString(4), results.getString(5)));
                 }
            }
            }
    }
    @FXML
    void viewStudents(ActionEvent event) throws IOException { //to display the students in a table who enrolled with the respective club
        stdNameTbl.refresh();
        selecteventmsg.setText(" ");
        displayStudent.clear();
        if (selecteventdropdown.getValue()!=null){
            try {
            clubMembers();
                 }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
            studentname.setCellValueFactory(new PropertyValueFactory<Attendance, String>("username1"));
            attendance.setCellValueFactory(new PropertyValueFactory<Attendance, String>("attendence"));
            stdNameTbl.setItems(displayStudent);
        } else {
            selecteventmsg.setText("Please Select An Event!");
        }


    }
}