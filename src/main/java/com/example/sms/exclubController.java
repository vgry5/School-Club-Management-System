package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import static com.example.sms.OOPCoursework.clublist;


public class exclubController  {
//    @FXML
//    public TableView<club> exclubtable;
//    @FXML
//    public TableColumn<club, String> name;
//    @FXML
//    public TableColumn<club, String> ;
    @FXML
    private AnchorPane container;
    @FXML
    private Button exclub;
    @FXML

    private Stage stage;
    private Scene scene;
    private Parent root;

    private void exclubs (ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("existingclubs.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
