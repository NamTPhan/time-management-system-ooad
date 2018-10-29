package controllers.timers.states;

public class BreakTimerState implements TimerState {
    Timer timer;

    public BreakTimerState(Timer newTimer) {
        timer = newTimer;
    }

    @Override
    public void start() {
        timer.timeline.playFromStart();
    }

    @Override
    public void pause() {
        timer.timeline.stop(); // stop the timer
        timer.setTimerState(timer.getPauseTimerState());
    }

    @Override
    public void stop() {
        timer.resetTimer(false);
        timer.setTimerState(timer.getIdleTimerState());
    }

    @Override
    public void startBreakTime() {
        // Cannot start a break while in a break
    }
}