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

    private final int MAIN_SETTINGS_INDEX_CONFIG = 0;
    private final int DEFAULT_DROPDOWN_OPTION = 0;
    private int settingsIdLoadedFromDb;

    private Settings settings = new Settings();
    private GenericDAO<Settings> settingsDao = new SettingsDaoImplementation();

    private void setComboBoxData() {
        // Set sound options
        ObservableList<String> soundOptions =
                FXCollections.observableArrayList(
                        "Phone Ringtone",
                        "Alarm Clock"
                );

        soundChoice.setItems(soundOptions);
        soundChoice.getSelectionModel()
                .select(settingsDao.getAll().size() != 0 ? settingsDao.getAll().get(MAIN_SETTINGS_INDEX_CONFIG).getSound()
                        : DEFAULT_DROPDOWN_OPTION);

        // Set round size options
        ObservableList<String> roundOptions =
                FXCollections.observableArrayList(
                        "2 rounds",
                        "4 rounds"
                );

        roundSizeChoice.setItems(roundOptions);
        roundSizeChoice.getSelectionModel().select(settingsDao.getAll().size() != 0 ? settingsDao.getAll().get(MAIN_SETTINGS_INDEX_CONFIG).getRoundSize()
                : DEFAULT_DROPDOWN_OPTION);
    }

    @FXML
    private void saveSettings(javafx.event.ActionEvent event) throws Exception {

        // Save settings and show button to go back to home
        successMessage.setVisible(true);
        saveSettings.setVisible(false);
        backToPomodoro.setVisible(true);

        // Save changes
        settings.setRoundSize(roundSizeChoice.getSelectionModel().getSelectedIndex());
        settings.setSessionGoal(Math.round((int) dailyGoalSlider.getValue()));
        settings.setSound(soundChoice.getSelectionModel().getSelectedIndex());
        settings.setLengthShortBreak(Math.round((int) shortBreakSlider.getValue()));
        settings.setLengthLongBreak(Math.round((int) longBreakSlider.getValue()));
        settingsDao.update(settingsIdLoadedFromDb, settings);

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

        // Check in the database if there is a configuration already saved else use the default settings
        if (settingsDao.getAll().size() == 0) {
            setSettingsValuesOnLoad(settings);
            settingsDao.save(settings);
        } else {
            setSettingsValuesOnLoad(settingsDao.getAll().get(MAIN_SETTINGS_INDEX_CONFIG));
        }

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
    }

    private void setSettingsValuesOnLoad(Settings settings) {

        settingsIdLoadedFromDb = settings.getSettingsId();

        // Elements
        dailyGoalSlider.setValue(settings.getSessionGoal());
        shortBreakSlider.setValue(settings.getLengthShortBreak());
        longBreakSlider.setValue(settings.getLengthLongBreak());

        // Labels
        dailyGoalLabelSlider.setText(String.valueOf(settings.getSessionGoal()));
        shortBreakLabelSlider.setText(String.valueOf(settings.getLengthShortBreak()));
        longBreakLabelSlider.setText(String.valueOf(settings.getLengthLongBreak()));
    }
}
