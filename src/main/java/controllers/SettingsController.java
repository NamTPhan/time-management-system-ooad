package controllers;

import controllers.timers.HourTimerController;
import controllers.timers.PomodoroTimerController;
import controllers.timers.TenMinuteTimerController;
import controllers.timers.TimerController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.GenericDAO;
import models.Settings;
import models.SettingsDaoImplementation;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * @author Nam Phan 500769669
 */
public class SettingsController implements Initializable {

    @FXML
    private ComboBox soundChoice, roundSizeChoice, timerChoice;
    @FXML
    private Slider dailyGoalSlider, shortBreakSlider, longBreakSlider;
    @FXML
    private Label dailyGoalLabelSlider, shortBreakLabelSlider, longBreakLabelSlider, successMessage;
    @FXML
    private Button backToPomodoro, saveSettings;

    private final int MAIN_SETTINGS_INDEX_CONFIG = 1;
    private final int DEFAULT_DROPDOWN_OPTION = 0;
    private int settingsIdLoadedFromDb;
    private static String soundFile;

    private Settings settings = new Settings();
    private GenericDAO<Settings> settingsDao = new SettingsDaoImplementation();
    private TimerController controller;

    /**
     * Method for loading combobox data on load screen
     */
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

        // Set round size options
        ObservableList<String> timerOptions =
                FXCollections.observableArrayList(
                        "25 minutes",
                        "60 minutes",
                        "10 minutes"
                );
        timerChoice.setItems(timerOptions);
        timerChoice.getSelectionModel().select(settingsDao.getAll().size() != 0 ? settingsDao.getAll().get(MAIN_SETTINGS_INDEX_CONFIG).getTimerType()
                : DEFAULT_DROPDOWN_OPTION);
    }

    /**
     * Handles button and message visibility and saves changes to database
     */
    @FXML
    private void saveSettings(javafx.event.ActionEvent event) {

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
        settings.setTimerType(timerChoice.getSelectionModel().getSelectedIndex());
        settingsDao.update(settingsIdLoadedFromDb, settings);
    }

    /**
     * Handles the close button of the settings screen and selects the right controller
     * based on database value
     */
    @FXML
    private void closeSettings() throws IOException {
        settings = settingsDao.getByIndex(MAIN_SETTINGS_INDEX_CONFIG);

        // Selects the controller that was just saved
        switch (settings.getTimerType()) {
            case 1:
                controller = new HourTimerController();
                break;
            case 2:
                controller = new TenMinuteTimerController();
                break;
            default:
                controller = new PomodoroTimerController();
                break;
        }

        // Loads Pomodoro window again and close current window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/session.fxml"));
        loader.setController(controller);
        Parent root1 = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Pomodoro");
        stage.setScene(new Scene(root1));
        stage.show();

        Stage stageCurrent = (Stage) backToPomodoro.getScene().getWindow();
        stageCurrent.close();
    }

    /**
     * Initialize settings data on load screen and updates the sliders values real time
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBoxData();

        // Check in the database if there is a configuration already saved, else use the default settings
        if (settingsDao.getAll().size() == 0) {
            // Load settings with all the default values
            setSettingsValuesOnLoad(settings);
            // Then save it in the database
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

    /**
     * Displays all the database data in the sliders and comboboxes
     *
     * @param settings object that contains all data for displaying
     */
    private void setSettingsValuesOnLoad(Settings settings) {
        // Get the unique id from the database that is used for the settings config
        settingsIdLoadedFromDb = settings.getSettingsId();

        // Elements
        dailyGoalSlider.setValue(settings.getSessionGoal());
        shortBreakSlider.setValue(settings.getLengthShortBreak());
        longBreakSlider.setValue(settings.getLengthLongBreak());

        // Labels value
        dailyGoalLabelSlider.setText(String.valueOf(settings.getSessionGoal()));
        shortBreakLabelSlider.setText(String.valueOf(settings.getLengthShortBreak()));
        longBreakLabelSlider.setText(String.valueOf(settings.getLengthLongBreak()));
    }

    public static void playSound(Settings settings) {
        switch (settings.getSound()) {
            case 0:
                soundFile = "src/main/resources/sounds/phone.mp3";
                break;
            case 1:
                soundFile = "src/main/resources/sounds/alarmclock.mp3";
                break;
            default:
                break;
        }
        Path p1 = Paths.get(soundFile);
        AudioClip clip = new AudioClip(p1.toUri().toString());

        clip.play();
    }
}
