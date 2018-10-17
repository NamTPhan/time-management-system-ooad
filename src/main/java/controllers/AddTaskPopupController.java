package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Task;

public class AddTaskPopupController {

    private Task task = new Task();

    @FXML
    private Button backToTaskOverview, submitNewTask;
    @FXML
    private ComboBox comboBoxActivity;
    @FXML
    private Label fromLabel, toLabel, wordsLabel;
    @FXML
    private TextField taskNameField, fromField, toField, wordsField;

    @FXML
    void initialize() {
        comboBoxActivity.setItems(FXCollections.observableArrayList("Reading", "Writing"));
        fromLabel.setVisible(false);
        toLabel.setVisible(false);
        wordsLabel.setVisible(false);
        fromField.setVisible(false);
        toField.setVisible(false);
        wordsField.setVisible(false);
    }

    public void backToTaskOverviewAction(ActionEvent event) {
        Stage stage = (Stage) backToTaskOverview.getScene().getWindow();
        stage.close();
    }

    public void comboBoxActivityAction(ActionEvent event) {
        System.out.println("Activity chosen");
        if (comboBoxActivity.getSelectionModel().getSelectedItem().equals("Reading")) {
            wordsLabel.setVisible(false);
            wordsField.setVisible(false);
            fromLabel.setVisible(true);
            toLabel.setVisible(true);
            fromField.setVisible(true);
            toField.setVisible(true);
        } else if (comboBoxActivity.getSelectionModel().getSelectedItem().equals("Writing")) {
            fromLabel.setVisible(false);
            toLabel.setVisible(false);
            fromField.setVisible(false);
            toField.setVisible(false);
            wordsLabel.setVisible(true);
            wordsField.setVisible(true);
        } else {
            System.out.println("Please choose either \"Reading\" or \"Writing\"!");
        }
    }

    public void submitNewTaskAction(ActionEvent event) {
        if (taskNameField.getText() == null || taskNameField.getText() == "") {
            //Error message
            return;
        } else if (comboBoxActivity.getSelectionModel().isEmpty()) {
            //Error message
            return;
        } else if (comboBoxActivity.getSelectionModel().getSelectedItem().equals("Reading")) {
            if (fromField.getText() == null || fromField.getText() == "" ||
                    toField.getText() == null || toField.getText() == "") {
                //Error message, need to be integers
                return;
            }
            task.saveReadingTask(taskNameField.getText(), Integer.parseInt(toField.getText()),
                    Integer.parseInt(fromField.getText()));
        } else if (comboBoxActivity.getSelectionModel().getSelectedItem().equals("Writing")) {
            if (wordsField.getText() == null || wordsField.getText() == "") {
                //Error message, needs to be an integer
                return;
            }
            task.saveWritingTask(taskNameField.getText(), Integer.parseInt(wordsField.getText()));
        }
    }
}
