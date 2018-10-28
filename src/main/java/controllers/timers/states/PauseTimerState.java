package controllers.timers.states;

public class PauseTimerState implements TimerState {
    Timer timer;

    public PauseTimerState(Timer newTimer) {
        timer = newTimer;
    }

    @Override
    public void start() {
        if (timer.timerSeconds <= 0 && timer.timerMinutes <= 0&& !timer.inBreakTime) { // Timer is done
            timer.enterBreak();
        } else {
            if (timer.inBreakTime) timer.setTimerState(timer.getBreakTimerState());
            else timer.setTimerState(timer.getRunningTimerState());

            timer.timeline.playFromStart();   // Resume timer
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
        // You can not manually enter this method, this is only possible when the timer has finished
    }
}