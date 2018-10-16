package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import models.Session;

import java.awt.*;

public abstract class  TimerController {
    // Initialize JavaFX object references
    @FXML
    protected Button playButton, pauseButton, stopButton;
    @FXML
    protected Label timerLabel;

    // Initialize essential timer variables
    protected static final int SECONDS_IN_MINUTE = 60;
    protected Session currentSession = new Session();
    protected final int DURATION;
    protected final Color COLOR;

    //private Session currentSession = new Session();
    private Timeline timeline;
    private int timerMinutes, timerSeconds;

    /**
     * This constructor is accessible from subclasses so users can create
     * subcontrollers that can use different timer durations
     *
     * Constructs a object of TimerController data type
     * with duration and standard color red
     * @param duration minutes in the timer
     */
    protected TimerController(int duration){
        DURATION = duration * SECONDS_IN_MINUTE;
        COLOR = Color.RED;
        timerMinutes = DURATION  / SECONDS_IN_MINUTE;
        timerSeconds = DURATION - (timerMinutes * SECONDS_IN_MINUTE);
    }

    /**
     * This constructor is accessible from subclasses so users can create
     * subcontrollers that can use different timer durations
     *
     * Constructs a object of TimerController data type
     * with duration and a given color
     * @param duration minutes in the timer
     * @param color of the timer
     */
    protected TimerController(int duration, Color color){
        DURATION = duration * SECONDS_IN_MINUTE;
        COLOR = color;
        timerMinutes = DURATION  / SECONDS_IN_MINUTE;
        timerSeconds = DURATION - (timerMinutes * SECONDS_IN_MINUTE);
    }

    public void initialize(){
        playButton.setOnAction(event ->  { startTimer(); });
        pauseButton.setOnAction(event -> { pauseTimer(); });
        stopButton.setOnAction(event ->  { stopTimer(); });

        viewUpdateTimer();
    }

    /**
     * Start a new session (or in future expansion to resume a old one)
     */
    public void startTimer(){
        if (currentSession == null){
            currentSession = new Session();
            setTimerToDefault();
        } else
            createNewTimer();

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
    public void pauseTimer(){
        updateInvestedTime();

        timeline.stop();
        toggleTimerButtons();
        System.out.println(currentSession.toString()); // This row is for testing #DELETE
    }

    /**
     * Stops the timer and resets the values
     */
    public void stopTimer(){
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
        int investedTime = DURATION - timeRemaining;
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
        timerMinutes = DURATION / SECONDS_IN_MINUTE;
        timerSeconds = (DURATION - (timerMinutes * SECONDS_IN_MINUTE));

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

}
