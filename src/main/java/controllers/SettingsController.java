package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private ComboBox soundChoice;
    @FXML
    private Slider dailyGoalSlider, shortBreakSlider;
    @FXML
    private Label dailyGoalLabelSlider, shortBreakLabelSlider, successMessage;
    @FXML
    private TextField longBreak;

    private void setComboBoxData(){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Phone Ringtone",
                        "Alarm Clock"
                );

        soundChoice.setItems(options);
        soundChoice.getSelectionModel().selectFirst();
    }

    @FXML
    private void saveSettings(){
        successMessage.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBoxData();

        // Set daily goal and update on screen when sliding
        dailyGoalSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                dailyGoalLabelSlider.textProperty().setValue(
                        String.valueOf(Math.floor((int)dailyGoalSlider.getValue())));

            }
        });

        // Set short break and update on screen when sliding
        shortBreakSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                shortBreakLabelSlider.textProperty().setValue(
                        String.valueOf(Math.floor((int) shortBreakSlider.getValue())));

            }
        });

        // Check if input is a number
        longBreak.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    longBreak.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
