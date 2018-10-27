package controllers.timers.states;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class StartTimer implements TimerState {
    Timer timer;

    public StartTimer(Timer newTimer) {
        timer = newTimer;
    }

    @Override
    public void start() {
        // You can't start more than one timer.
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
        // You can not manually enter in here only when the timer is finished
    }
}
