package controllers.timers.states;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class StartBreak implements TimerState {
    Timer timer;

    public StartBreak(Timer newTimer) {
        timer = newTimer;
    }

    public void initiateState() {


        timer.setTimerState(timer.getIdleTimerState());


        timer.timerFinished = true;
        timer.timerState = timer.startBreakTimer;
    }

    @Override
    public void start() {
        // You cannot start two timers at once
    }

    @Override
    public void pause() {
        // Timer paused.
        timer.timeline.stop(); // stop the timer
        timer.setTimerState(timer.getPauseTimerState());
    }

    @Override
    public void stop() {
        System.out.println("Timer stopped.");
        timer.resetTimer(false);
        timer.setTimerState(timer.getIdleTimerState());
    }

    @Override
    public void startBreakTime() {

    }
}
