package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Session;
import models.SetStage;

public class SessionController {

    @FXML
    private Button playButton, pauseButton, stopButton;
    @FXML
    private Label timerLabel;

    private static final int SECONDS_IN_MINUTE = 60;
    private static final Integer START_TIME = (25 * SECONDS_IN_MINUTE);

    private Session currentSession = new Session();
    private Timeline timeline;
    private int timerMinutes = START_TIME  / SECONDS_IN_MINUTE;
    private int timerSeconds = START_TIME  - (timerMinutes * SECONDS_IN_MINUTE);

    public void initialize(){
        playButton.setOnAction(event ->  { startSession(); });
        pauseButton.setOnAction(event -> { pauseSession(); });
        stopButton.setOnAction(event ->  { stopSession(); });

        viewUpdateTimer();
    }

    /**
     * Start a new session (or in future expansion to resume a old one)
     */
    private void startSession(){

        if (currentSession == null){
            currentSession = new Session();
            setTimerToDefault();
        } else {
            createNewTimer();
        }

        toggleTimerButtons();     // Visually toggle between play and pause buttons

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    timerSeconds--; // Decrease the seconds of the timer every second
                    viewUpdateTimer();  // Visually update timer
                }));
        timeline.playFromStart();
    }

    /**
     * Temporarily stops the timer to resume later
     */
    private void pauseSession(){
        updateInvestedTime();

        timeline.stop();
        toggleTimerButtons();
        System.out.println(currentSession.toString()); // This row is for testing #DELETE
    }

    /**
     * Stops the timer and resets the values
     */
    private void stopSession(){
        updateInvestedTime();
        System.out.println(currentSession.toString()); // This row is for testing #DELETE
        currentSession = null; // Remove the reference to the object

        timeline.stop();
        setTimerToDefault();
        toggleTimerButtons();
    }

    /**
     *  Calculates and updates the time that is invested in a task
     */
    private void updateInvestedTime() {
        int timeRemaining = ((timerMinutes * 60) + timerSeconds);
        int investedTime = START_TIME - timeRemaining;
        currentSession.setInvestedTime(investedTime);
    }

    /**
     * Toggles between the visibility of the play and pause button.
     */
    private void toggleTimerButtons(){
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
        createNewTimer();

        // Converts the minutes and seconds
        timerMinutes = START_TIME / SECONDS_IN_MINUTE;
        timerSeconds = (START_TIME - (timerMinutes * SECONDS_IN_MINUTE));

        viewUpdateTimer();
    }

    /**
     *  Creates a new timer
     */
    private void createNewTimer(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Visually update the timer based on the timers variables
     */
    private void viewUpdateTimer() {
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

    public void taskOverviewAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/views/taskOverview.fxml");
    }
}

