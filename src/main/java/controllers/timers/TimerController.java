package controllers.timers;

import controllers.buttons.ButtonVisibilityBehavior;
import controllers.timers.states.TimerState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import models.Session;

import java.awt.*;

public abstract class TimerController {
    // Initialize JavaFX object references
    @FXML
    public static Button playButton, pauseButton, stopButton;
    @FXML
    private Label timerLabel;

    // Initialize essential timer variables
    protected final int DURATION, BREAK_DURATION;
    protected static final int SECONDS_IN_MINUTE = 60;
    protected Session currentSession = new Session();

    protected Color timerColor = Color.getHSBColor(0, 68, 66),
                    breakColor = Color.getHSBColor(206, 68, 66);

    protected ButtonVisibilityBehavior buttonBehavior;

    // private Session currentSession = new Session();
    private Timeline timeline;
    private int timerMinutes, timerSeconds, breakMinutes, breakSeconds;

    /**
     * This constructor is accessible from subclasses so users can create
     * subcontrollers that can use different timer durations
     * <p>
     * Constructs a object of TimerController data type
     * with duration and standard color red
     *
     * @param duration      minutes in the timer
     * @param breakDuration minutes of a break after the timer finished
     */
    protected TimerController(int duration, int breakDuration) {
        DURATION       = duration * SECONDS_IN_MINUTE;
        BREAK_DURATION = breakDuration * SECONDS_IN_MINUTE;

        timerMinutes = DURATION / SECONDS_IN_MINUTE;
        timerSeconds = DURATION - (timerMinutes * SECONDS_IN_MINUTE);

        breakMinutes = BREAK_DURATION / SECONDS_IN_MINUTE;
        breakSeconds = BREAK_DURATION - (breakDuration * SECONDS_IN_MINUTE);
    }

    public void initialize() {
        playButton.setOnAction(event -> {
            startTimer();
        });
        pauseButton.setOnAction(event -> {
            pauseTimer();
        });
        stopButton.setOnAction(event -> {
            stopTimer();
        });

        viewUpdateTimer();
    }

    /**
     * Start a new session (or in future expansion to resume a old one)
     */
    public void startTimer() {
        if (currentSession == null) {
            currentSession = new Session();
            setTimerToDefault();
        } else {
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
        }

        buttonBehavior.turnButtonsOn();     // Visually toggle between play and pause buttons

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    timerSeconds--; // Decrease the seconds of the timer every second
                    viewUpdateTimer();  // Visually update timer
                }));
        timeline.playFromStart();

        // TIMER OVER? ENTER BREAK STATE
    }

    /**
     * Temporarily stops the timer to resume later
     */
    public void pauseTimer() {
        updateInvestedTime();

        timeline.stop();
        buttonBehavior.turnButtonsOff();
        System.out.println(currentSession.toString()); // This row is for testing #DELETE
    }

    /**
     * Stops the timer and resets the values
     */
    public void stopTimer() {
        updateInvestedTime();
        System.out.println(currentSession.toString()); // This row is for testing #DELETE
        currentSession = null; // Remove the reference to the object

        timeline.stop();
        setTimerToDefault();
        buttonBehavior.sessionEndsResetButtons();
    }

    /**
     * Start a break. The time is dependent on the type of timer
     * @param duration  of the break, in seconds
     * @param color     color of the background
     */
    private void initiateBreak(int duration, Color color) {

    }

    /**
     * Calculates and updates the time that is invested in a task
     */
    private void updateInvestedTime() {
        int timeRemaining = ((timerMinutes * 60) + timerSeconds);
        int investedTime = DURATION - timeRemaining;
        currentSession.setInvestedTime(investedTime);
    }

    /**
     * Resets the timer to it's original time.
     */
    private void setTimerToDefault() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Converts the minutes and seconds
        timerMinutes = DURATION / SECONDS_IN_MINUTE;
        timerSeconds = (DURATION - (timerMinutes * SECONDS_IN_MINUTE));

        viewUpdateTimer();
    }

    /**
     * Visually update the timer based on the timers variables
     */
    private void viewUpdateTimer() {
        // Corrects minutes and seconds when they are out of bounds
        if (timerSeconds < 0) {
            timerMinutes--;
            timerSeconds = SECONDS_IN_MINUTE - 1;
        }

        // Visually update the timer
        timerLabel.setText(String.format("%1$02d:%2$02d", timerMinutes, timerSeconds));

        if (timerSeconds <= 0 && timerMinutes <= 0)
            timeline.stop(); // Stop timer if it is at zero
    }

}
