package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.SetStage;
import models.Task;

import java.util.ArrayList;

public class taskOverviewController {

    private Task task = new Task();

    @FXML
    private TableView<Task> tableView;
    @FXML
    private TableColumn<Task, String> taskNameColumn;

    @FXML
    void initialize() {
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        tableView.setItems(getTasks());
    }

    public ObservableList<Task> getTasks() {
        ObservableList<Task> elements = FXCollections.observableArrayList();
        Task task = new Task();
        ArrayList<Task> taskList = task.getAllTasks();
        for (Task tasks : taskList) {
            elements.add(tasks);
        }
        return elements;
    }

    public void backAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/views/session.fxml");
    }

    public void addAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addTaskPopup.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Add Tasks");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load new window");
        }
    }

    public void editAction(ActionEvent event) {
        //Edit task
    }

    public void deleteAction(ActionEvent event) {
        task.deleteTask();
    }
}