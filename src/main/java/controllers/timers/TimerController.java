package controllers.timers;

import controllers.buttons.ButtonVisibilityBehavior;
import controllers.timers.states.Timer;
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
    protected ButtonVisibilityBehavior buttonBehavior;

    // Initialize essential timer variables
    protected Session currentSession = new Session();

    protected Color backgroundColorWhenRunning = Color.getHSBColor(0, 68, 66),
                    backgroundColorWhenInBreak = Color.getHSBColor(206, 68, 66);

    private Timer timer;

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
        timer = new Timer(duration, breakDuration);
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

    }

    /**
     * Start a new session (or in future expansion to resume a old one)
     */
    public void startTimer() {
        currentSession = (currentSession == null) ? new Session() : currentSession;
        timer.start();
        buttonBehavior.turnButtonsOn();     // Visually toggle between play and pause buttons
    }

    /**
     * Temporarily stops the timer to resume later
     */
    public void pauseTimer() {
        timer.pause();
        buttonBehavior.turnButtonsOff();
        updateInvestedTime();
    }

    /**
     * Stops the timer and resets the values
     */
    public void stopTimer() {
        currentSession = null; // Remove the reference to the object
        timer.stop();
        buttonBehavior.sessionEndsResetButtons();
        updateInvestedTime();
    }


    /**
     * Calculates and updates the time that is invested in a task
     */
    public void updateInvestedTime() {
        currentSession.setInvestedTime(timer.getInvestedTime());
    }
}
