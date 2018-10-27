package controllers.timers.states;

import controllers.buttons.ButtonVisibilityBehavior;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer implements TimerState {
    @FXML
    private Label timerLabel;

    protected static final int SECONDS_IN_MINUTE = 60;
    protected final int DURATION, BREAK_DURATION;

    TimerState startTimer;
    TimerState pauseTimer;
    TimerState startBreakTimer;
    TimerState idleTimer;

    TimerState timerState;

    protected int timerMinutes, timerSeconds, timerDuration, breakDuration, totalInvestedTime;
    boolean timerFinished = false;
    public Timeline timeline = new Timeline();

    public Timer(int timerDuration, int breakDuration) {
        timeline.setCycleCount(Timeline.INDEFINITE);

        DURATION       = timerDuration * SECONDS_IN_MINUTE;
        BREAK_DURATION = breakDuration * SECONDS_IN_MINUTE;

        timerMinutes = DURATION / SECONDS_IN_MINUTE;
        timerSeconds = DURATION - (timerMinutes * SECONDS_IN_MINUTE);

        startTimer = new StartTimer(this);
        pauseTimer = new PauseTimer(this);
        startBreakTimer = new StartBreak(this);
        idleTimer = new IdleTimer(this);

        timerState = idleTimer;

        if (timerMinutes < 0 && timerSeconds < 0) {
            timerState = startBreakTimer;
            timerFinished = true;
        }
    }

    void setTimerState(TimerState newTimerState){
        timerState = newTimerState;
    }

    public int getInvestedTime(){
        return timerDuration - ((timerMinutes * 60) + timerSeconds);
    }

    /**
     * Visually update the timer based on the timers variables
     */
    protected void viewUpdateTimer() {
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

    protected void resetTimer(boolean asABreak) {
        // Create a new timer
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Converts the minutes and seconds and
        // Resets the timer to it's original time or to a break time
        if (asABreak) {
            timerMinutes = BREAK_DURATION / SECONDS_IN_MINUTE;
            timerSeconds = (BREAK_DURATION - (timerMinutes * SECONDS_IN_MINUTE));
        } else {
            timerMinutes = DURATION / SECONDS_IN_MINUTE;
            timerSeconds = (DURATION - (timerMinutes * SECONDS_IN_MINUTE));
        }
        viewUpdateTimer();    // Update the view
    }


    protected void enterBreak() {
        resetTimer(true);

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (timerSeconds <= 0){       // Timer is done
                        resetTimer(false);
                        setTimerState(getIdleTimerState());
                    }
                    else {
                        timerSeconds--;           // Decrease the seconds of the timer every second
                        viewUpdateTimer();        // Visually update timer
                    }
                }));
        timeline.playFromStart();

        setTimerState(getBreakTimerState());
    }


    public void start() {
        timerState.start();
    }

    public void pause() {
        timerState.pause();
    }

    public void stop() {
        timerState.stop();
    }

    public void startBreakTime() {
        timerState.startBreakTime();
    }


    public TimerState getStartTimerState() { return startTimer;}
    public TimerState getPauseTimerState() { return pauseTimer;}
    public TimerState getBreakTimerState() { return startBreakTimer;}
    public TimerState getIdleTimerState() { return idleTimer;}























    public void setTimerDuration(int newDuration) {
        timerDuration = newDuration;
    }

}
