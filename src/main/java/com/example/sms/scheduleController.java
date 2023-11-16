package com.example.sms;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class scheduleController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField EventName;
    @FXML
    private ComboBox<String> clubSelect;
    @FXML
    private DatePicker date;
    @FXML
    private TextField description;
    private DatabaseConnection connectSchedule;
    ArrayList<String> clublist = new ArrayList<>();
    public void addEvent(ActionEvent actionEvent) throws IOException, SQLException {
        String eventName = EventName.getText();
        String club = clubSelect.getValue();
        String Date = date.getValue().format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
        String Description = description.getText();

        event Event1 = new event(eventName, club, Date, Description);
        String insertQuery =
                "INSERT INTO events (`Event Name`, `club`, `date`, `Description`) VALUES (?, ?, ?, ?)";

        Connection connection = connectSchedule.connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, Event1.getEventName());
            preparedStatement.setString(2, Event1.getClubName());
            preparedStatement.setString(3, Event1.getDate());
            preparedStatement.setString(4, Event1.getDescription());


            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Data insertion failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calender.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root, 744, 689);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            previuosClubs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> clubs1 = clubSelect.getItems();
        int index = 0;
        while (index < clublist.size()) {
            clubs1.add(clublist.get(index));
            index++;
        }


    }


    private void previuosClubs() throws SQLException {
        String selectQuery = "SELECT * FROM `clubs`;";
        Connection comm = connectSchedule.connect();
        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                club club = new club(results.getString(1), results.getString(2), results.getString(3), results.getInt(4));
                clublist.add(club.getName());
            }
        }
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


