package controllers.timers.states;

import controllers.timers.TimerController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer implements TimerState {
    @FXML
    private Label timerLabel;

    // Mics variables
    private final TimerController timerController;

    // State variables
    private TimerState timerState;
    private final TimerState RUNNING_TIMER_STATE,
            PAUSE_TIMER_STATE,
            BREAK_TIMER_STATE,
            IDLE_TIMER_STATE;
    public boolean inBreakTime = false;

    // Timer variables
    protected static final int SECONDS_IN_MINUTE = 60;
    protected final int DURATION, BREAK_DURATION;
    protected Timeline timeline;
    protected int timerMinutes,
            timerSeconds,
            timerDuration,
            breakDuration,
            totalInvestedTime;

    public Timer(int timerDuration, int breakDuration, TimerController controller) {
        // Set timer ready 
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Initialize timer variables
        DURATION = timerDuration * SECONDS_IN_MINUTE;
        BREAK_DURATION = breakDuration * SECONDS_IN_MINUTE;
        timerMinutes = DURATION / SECONDS_IN_MINUTE;
        timerSeconds = DURATION - (timerMinutes * SECONDS_IN_MINUTE);

        // Set references to the states
        RUNNING_TIMER_STATE = new RunningTimerState(this);
        PAUSE_TIMER_STATE = new PauseTimerState(this);
        BREAK_TIMER_STATE = new BreakTimerState(this);
        IDLE_TIMER_STATE = new IdleTimerState(this);

        timerState = IDLE_TIMER_STATE;  // Set first state(default)
        timerController = controller;   // 

        if (timerMinutes < 0 && timerSeconds < 0) { // Timer finished by first initialization?
            timerState = BREAK_TIMER_STATE;
            inBreakTime = true;
        }
    }

    /**
     * Initialize FXML reference variables
     *
     * @param timerLabel Label of the timer(minutes and seconds)
     */
    public void setupFXMLReferences(Label timerLabel) {
        // Initialize FXML variables
        this.timerLabel = timerLabel;

        // Update the FXML elements(view) with the timer variables
        viewUpdateTimer();
    }

    /**
     * Change the state of the timer
     *
     * @param newTimerState state in which the timer will be
     */
    protected void setTimerState(TimerState newTimerState) {
        timerState = newTimerState;
    }

    /**
     * Return the time that is invested in the timer
     *
     * @return INT invested time
     */
    public int getInvestedTime() {
        return timerDuration - ((timerMinutes * 60) + timerSeconds);
    }

    /**
     * Visually update the timer based on the timer variables
     */
    protected void viewUpdateTimer() {
        // Corrects minutes and seconds when they are out of bounds
        if (timerSeconds < 0) {
            timerMinutes--;
            timerSeconds = SECONDS_IN_MINUTE - 1; // Start at 59 not 60
        }

        // Visually update the timer
        timerLabel.setText(String.format("%1$02d:%2$02d", timerMinutes, timerSeconds));

        if (timerSeconds <= 0 && timerMinutes <= 0)
            timeline.stop(); // Stop timer if it is at zero
    }

    /**
     * Bring the timer back to the start
     * Can be used for resetting to normal or break time
     *
     * @param isABreak
     */
    protected void resetTimer(boolean isABreak) {
        // Create a new timer
        timeline.stop();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Set timer back to its starting position as a normal/break timer
        if (isABreak) {
            timerMinutes = BREAK_DURATION / SECONDS_IN_MINUTE;
            timerSeconds = (BREAK_DURATION - (timerMinutes * SECONDS_IN_MINUTE));
        } else {
            timerMinutes = DURATION / SECONDS_IN_MINUTE;
            timerSeconds = (DURATION - (timerMinutes * SECONDS_IN_MINUTE));
        }

        viewUpdateTimer();
    }

    /**
     * Function will be triggerd after the timer has run out of seconds.
     * Starts a new timer as a break timer an prepares everything to start
     * all over again as a normal timer
     */
    protected void enterBreak() {
        resetTimer(true);   // Reset to a break timer

        // Run break timer 
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (timerSeconds <= 1 && timerMinutes <= 0) { // Break is finished
                        resetTimer(false);                  // Reset as to a normal timer
                        timerController.finishBreakTime();  // Let the controller know the break is finished
                        setTimerState(getIdleTimerState());
                        inBreakTime = false;
                    } else {
                        timerSeconds--;           // Decrease the seconds of the timer every second
                        viewUpdateTimer();
                    }
                }));
        timeline.playFromStart();

        timerController.enterBreakTime();   // Let the controller know the timer is finished
        timerState.startBreakTime();
    }

    @Override
    public void start() {
        timerState.start();
    }

    @Override
    public void pause() {
        timerState.pause();
    }

    @Override
    public void stop() {
        timerState.stop();
    }

    @Override
    public void startBreakTime() {
        timerState.startBreakTime();
    }

    public TimerState getRunningTimerState() {
        return RUNNING_TIMER_STATE;
    }

    public TimerState getPauseTimerState() {
        return PAUSE_TIMER_STATE;
    }

    public TimerState getBreakTimerState() {
        return BREAK_TIMER_STATE;
    }

    public TimerState getIdleTimerState() {
        return IDLE_TIMER_STATE;
    }
}