package controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import models.SetStage;
import models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class taskOverviewController {

    // Might have to change this to a TableView later


    @FXML
    private ListView listView;

    @FXML
    void initialize() {
        //  Test data to put on the task overview page.
        ObservableList elements = FXCollections.observableArrayList();
        elements.add("task1");
        elements.add("reading");
        elements.add("writing");
        elements.add("Algorithms pages 1-30");
        listView.setItems(elements);
    }

    public void backAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/views/main.fxml");
    }

}
