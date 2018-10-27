package controllers.timers.states;

public class PauseTimer implements TimerState {
    Timer timer;

    public PauseTimer(Timer newTimer) {
        timer = newTimer;
    }

    @Override
    public void start() {
        // Resume timer
        if (timer.timerSeconds <= 0 && timer.timerMinutes <= 0) {
            timer.enterBreak();
        } else {
            timer.timeline.playFromStart();   // Resume timer
            timer.setTimerState(timer.getStartTimerState());
        }
    }

    @Override
    public void pause() {
        // A paused timer can not be paused again
    }

    @Override
    public void stop() {
        // A paused timer can not be stopped
    }

    @Override
    public void startBreakTime() {
        // You can not manually enter in here only when the timer is finished
    }
}
