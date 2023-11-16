package com.example.sms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class studentclubscontroller {

    @FXML
    private TableColumn<?, ?> advisorcol;

    @FXML
    private TableView<?> clubtable;

    @FXML
    private AnchorPane container;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TableColumn<?, ?> studentscol;

    private Stage stage; //create variables for scene, stage and root
    private Scene scene;
    private Parent root;

    @FXML
    void back(ActionEvent event)throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studentmenu.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
