package controllers;

import javafx.scene.Node;
import javafx.stage.Stage;
import models.SetStage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class MainController {

    @FXML
    private Button playButton, pauseButton, stopButton;
    @FXML
    private Label timerLabel;

    private static final int SECONDS_IN_MINUTE = 60;
    private static final Integer START_TIME = (25 * SECONDS_IN_MINUTE);

    private Timeline timeline;
    private int timerMinutes = START_TIME  / SECONDS_IN_MINUTE;
    private int timerSeconds = START_TIME  - (timerMinutes * SECONDS_IN_MINUTE);

    public void initialize(){
        playButton.setOnAction(event ->  { startSession(); });
        pauseButton.setOnAction(event -> { pauseSession(); });

        updateTimer();
    }

    /**
     * Start a new session (or in future expansion to resume a old one)
     */
    private void startSession(){
        setTimerToDefault();        // Reset timer
        toggleSessionButtons();     // Visually toggle between play and pause buttons

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    timerSeconds--; // Decrease the seconds of the timer every second
                    updateTimer();  // Visually update timer
                }));
        timeline.playFromStart();
    }

    /**
     * Temporarily stops the timer to resume later
     */
    private void pauseSession(){
        timeline.stop();

        toggleSessionButtons();
    }

    /**
     * Stops the timer and resets the values
     */
    private void stopSession(){

    }

    /**
     * Toggles between the visibility of the play and pause button.
     */
    private void toggleSessionButtons(){
        if (playButton.isVisible()) { // Hide play button, show pause and stop button
            playButton.setVisible(false);
            pauseButton.setVisible(true);
            stopButton.setVisible(true);
        } else if (pauseButton.isVisible()) { // Hide pause and stop button, show play button
            playButton.setVisible(true);
            pauseButton.setVisible(false);
            stopButton.setVisible(false);
        }
    }

    /**
     * Resets the timer to it's original time.
     */
    private void setTimerToDefault() {
        if (timeline != null)
            timeline.stop(); // Stop the old timer, if needed

        // Create a new timer
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Converts the minutes and seconds
        timerMinutes = START_TIME / SECONDS_IN_MINUTE;
        timerSeconds = (START_TIME - (timerMinutes * SECONDS_IN_MINUTE));
    }

    /**
     * Visually update the timer based on the timers variables
     */
    private void updateTimer() {
        // Corrects minutes and seconds when they are out of bounds
        if (timerSeconds < 0){
            timerMinutes--;
            timerSeconds = SECONDS_IN_MINUTE - 1;
        }

        // Visually update the timer
        timerLabel.setText(String.format("%1$02d:%2$02d", timerMinutes, timerSeconds));

        if (timerSeconds <= 0 && timerMinutes <= 0)
            timeline.stop(); // Stop timer if it is at zero
    }

    public void loadSettingsScene(javafx.event.ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/settings.fxml");
    }
}

