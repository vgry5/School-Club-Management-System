//package com.example.sms;
//
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Objects;
//import java.util.ResourceBundle;
//
//public class scheduleController implements Initializable {
//
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//    @FXML
//    private TextField EventName;
//    @FXML
//    private TextField Location;
//    @FXML
//    private ComboBox<club> clubSelect;
//    @FXML
//    private DatePicker date;
//    @FXML
//    private ComboBox<String> eventType;
//    private DatabaseConnection connectSchedule;
//    ArrayList<String> eventTypeList = new ArrayList<>();
//    ArrayList<String> clublist = new ArrayList<>();
//
//    public void eventSchedule(ActionEvent event) throws IOException {
//
//
//    }
//
//    public void calender(ActionEvent actionEvent) throws IOException {   // going to calendar view
//
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calender.fxml")));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root, 744, 689);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            previuosClubs();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String Meeting = "Meeting";
//        String Event = "Event";
//        String Activity = "Activity";
//        eventTypeList.add(Meeting);
//        eventTypeList.add(Event);
//        eventTypeList.add(Activity);
//        ObservableList<String> item = eventType.getItems();
//        item.add(eventTypeList.get(0));
//        item.add(eventTypeList.get(1));
//        item.add(eventTypeList.get(2));
//        //ObservableList<String> clubs1 = clubSelect.getItems();
////        int index = 0;
////        while (index < clublist.size()){
////            clubs1.add(clublist.get(1));
////            index++;
////        }
////
//
//
//    }
//
////    private void previuosClubs() throws SQLException {
////        String selectQuery = "SELECT * FROM `clubs`;";
////        Connection comm = connectSchedule.connect();
////        try (PreparedStatement statement = comm.prepareStatement(selectQuery)) {
////            ResultSet results = statement.executeQuery();
////            while (results.next()) {
////                club club = new club(results.getString(1),results.getString(2),results.getInt(3));
////                clublist.add(club.getName());
////
////            }
////
////        }
//
//    }
//
//}
