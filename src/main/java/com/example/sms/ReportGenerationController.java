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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportGenerationController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label reportMessage;
    @FXML
    private ComboBox<String> reportSelect;
    @FXML

    private Button backbutton;

    @FXML
    private TableView<event> clubActivities;
    @FXML
    private TableColumn<event, String> eventNameA;
    @FXML
    private TableColumn<event, String> clubNameA;
    @FXML
    private TableColumn<event, String> EventType;
    @FXML
    private TableView<club> clubMembership;
    @FXML
    private TableColumn<club, Integer> NoofStudentC;
    @FXML
    private TableColumn<club, String> clubNameC;
    @FXML
    private TableColumn<club, String> AdviserNameC;
    @FXML
    private TableView<Attendance> clubAttendence;
    @FXML
    private TableColumn<Attendance, Integer> noOfStudent;

    @FXML
    private TableColumn<Attendance, String> clubName;
    @FXML
    private TableColumn<Attendance, String> eventName;

    @FXML
    private Button generateButton;

    private DatabaseConnection connectReport;

    ArrayList<Attendance> attendances = new ArrayList<>();


    @FXML
    void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void generate(ActionEvent event) throws IOException, SQLException {
        String report = reportSelect.getValue();
        if (report != null) {
            if (report.equals("club membership")) {
                ClubMembership();
            } else if (report.equals("event attendance")) {
                eventAttendence();
            } else if (report.equals("club activities")) {
                clubActivities();
            }
        } else {
            reportMessage.setText("Select a report Type");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> report = FXCollections.observableArrayList("club membership", "event attendance", "club activities");
        reportSelect.setItems(report);
    }

    private void ClubMembership() {


    }

    private void eventAttendence() throws SQLException {
        attendances.clear();
        Attendece();
        ObservableList<Attendance> AttendenceOberver = FXCollections.observableList(attendances);
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName1"));
        eventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        noOfStudent.setCellValueFactory(new PropertyValueFactory<>("no_students"));
        clubAttendence.setItems(AttendenceOberver);
        clubAttendence.toFront();
    }

    private void clubActivities() throws SQLException {
        CalenderController.eventList.clear();
        previousActivity();
        ObservableList<event> EventOberver = FXCollections.observableList(FXCollections.observableList(CalenderController.eventList));
        clubNameA.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        eventNameA.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        EventType.setCellValueFactory(new PropertyValueFactory<>("EventType"));
        clubActivities.setItems(EventOberver);
        clubActivities.toFront();

    }

    private void previousActivity() throws SQLException {
        String selectQuery = "SELECT * FROM `events`;";
        Connection comm = connectReport.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                event pastEvent = new event(results.getString(1), results.getString(2), results.getString(3));
                CalenderController.eventList.add(pastEvent);
            }
        }
    }

    private void Attendece() throws SQLException {
        String selectQuery = "SELECT EventName,clubName, COUNT(*) AS countRec FROM attendanc GROUP BY EventName;";
        Connection comm = connectReport.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                String eventName = results.getString("EventName");
                String club =results.getString("clubName");
                int count = results.getInt("countRec");
                Attendance attendece = new Attendance(eventName,club, count);
                attendances.add(attendece);
            }
        }

    }
}
