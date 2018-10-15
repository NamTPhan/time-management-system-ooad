package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import models.GenericDAO;
import models.SetStage;
import models.Settings;
import models.SettingsDaoImplementation;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private ComboBox soundChoice, roundSizeChoice;
    @FXML
    private Slider dailyGoalSlider, shortBreakSlider, longBreakSlider;
    @FXML
    private Label dailyGoalLabelSlider, shortBreakLabelSlider, longBreakLabelSlider, successMessage;
    @FXML
    private Button backToPomodoro, saveSettings;

    private Settings settings = new Settings();
    private int selectedRoundSize;
    private GenericDAO<Settings> settingsDao = new SettingsDaoImplementation();

    private void setComboBoxData() {
        // Set sound options
        ObservableList<String> soundOptions =
                FXCollections.observableArrayList(
                        "Phone Ringtone",
                        "Alarm Clock"
                );

        soundChoice.setItems(soundOptions);
        soundChoice.getSelectionModel().select(0);

        // Set round size options
        ObservableList<String> roundOptions =
                FXCollections.observableArrayList(
                        "2 rounds",
                        "4 rounds"
                );

        roundSizeChoice.setItems(roundOptions);
        roundSizeChoice.getSelectionModel().select(0);
    }

    @FXML
    private void saveSettings(javafx.event.ActionEvent event) throws Exception {

        // Save settings and show button to go back to home
        successMessage.setVisible(true);
        saveSettings.setVisible(false);
        backToPomodoro.setVisible(true);

        // Check which round size is selected, index 0 = 2 rounds, index 1 = 4 rounds
        switch (roundSizeChoice.getSelectionModel().getSelectedIndex()) {
            case 0:
                selectedRoundSize = 2;
                break;
            case 1:
                selectedRoundSize = 4;
                break;
            default:
                break;
        }

        // Dao getByIndex method test
        settingsDao.getByIndex(1);
        System.out.println(settingsDao.getByIndex(1).getLengthLongBreak());

        // Dao save method test
        settings.setRoundSize(selectedRoundSize);
        settings.setSessionGoal(Math.round((int) dailyGoalSlider.getValue()));
        settings.setSound(soundChoice.getSelectionModel().getSelectedIndex());
        settings.setLengthShortBreak(Math.round((int) shortBreakSlider.getValue()));
        settings.setLengthLongBreak(Math.round((int) longBreakSlider.getValue()));
        settingsDao.save(settings);

        // Test to get selected values
//        System.out.println(soundChoice.getSelectionModel().getSelectedIndex());
//        System.out.println(selectedRoundSize);
//        System.out.println(Math.round((int) dailyGoalSlider.getValue()));

        // Go back to home on click listener
        backToPomodoro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                try {
                    new SetStage(stage, "/views/session.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBoxData();

        // Set daily goal and update on screen when sliding
        dailyGoalSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                dailyGoalLabelSlider.textProperty().setValue(
                        String.valueOf(Math.round((int) dailyGoalSlider.getValue())));

            }
        });

        // Set short break and update on screen when sliding
        shortBreakSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                shortBreakLabelSlider.textProperty().setValue(
                        String.valueOf(Math.round((int) shortBreakSlider.getValue())));

            }
        });

        // Set long break and update on screen when sliding
        longBreakSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                longBreakLabelSlider.textProperty().setValue(
                        String.valueOf(Math.round((int) longBreakSlider.getValue())));

            }
        });

        // Check if input is a number, commented out currently not needed, maybe later
//        longBreak.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    longBreak.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });
    }
}
