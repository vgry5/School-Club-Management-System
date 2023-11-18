package com.example.sms;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class InchargeclubController implements Initializable {
    @FXML
    private TableView<Students> studtable;
    @FXML
    private TableColumn<Students, String> usernamecol;
    @FXML
    private TableColumn<Students, String> namecol;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private DatabaseConnection connectRegister;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamecol.setCellValueFactory(new PropertyValueFactory<Students,String>("username"));
        namecol.setCellValueFactory(new PropertyValueFactory<Students,String >("firstname"));
        for(int i=0; i<OOPCoursework.clublist.size(); i++){
            if(OOPCoursework.clublist.get(i).getAdvisorID().equals(stafflogincontroller.username1)){
                for( int x=0; x<OOPCoursework.studentList.size();x++){
//                    if(OOPCoursework.studentList.get(i).Students){
//                        studtable.getItems().add(OOPCoursework.studentList.get(i).getFirstname());
                    }
                }
            }
        }
    @FXML
    void back (ActionEvent event)throws IOException{

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("advisor.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
