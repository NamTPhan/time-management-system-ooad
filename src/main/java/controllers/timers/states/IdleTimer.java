package controllers.timers.states;

import javafx.animation.KeyFrame;
import javafx.util.Duration;

import javax.xml.bind.SchemaOutputResolver;

public class IdleTimer implements TimerState {
    Timer timer;

    public IdleTimer(Timer newTimer) {
        timer = newTimer;
    }

    @Override
    public void start() {
        // Started the timer.
        timer.timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (timer.timerSeconds <= 0) { // Timer is done
                        timer.enterBreak();
                    }
                    else {
                        timer.timerSeconds--;         // Decrease the seconds of the timer every second
                        timer.viewUpdateTimer();      // Visually update timer
                    }
                }));
        timer.timeline.playFromStart();

        timer.setTimerState(timer.getStartTimerState());
    }

    @Override
    public void pause() {
        // You are not able to pause when the timer is not active.
    }

    @Override
    public void stop() {
        // You are not able to stop when the timer is not active.
    }

    @Override
    public void startBreakTime() {
        // You are not able to take a break while idling.
    }
}
