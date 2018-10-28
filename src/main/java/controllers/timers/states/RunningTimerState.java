package controllers.timers.states;

public class RunningTimerState implements TimerState {
    Timer timer;

    public RunningTimerState(Timer newTimer) {
        timer = newTimer;
    }

    @Override
    public void start() {
        // You can't start more than one timer.
    }

    @Override
    public void pause() {
        timer.timeline.stop();
        timer.setTimerState(timer.getPauseTimerState());
    }

    @Override
    public void stop() {
        timer.resetTimer(false);
        timer.setTimerState(timer.getIdleTimerState());
    }

    @Override
    public void startBreakTime() {
        // You can not manually enter this method, this is only possible when the timer has finished
        timer.inBreakTime = true;
        timer.setTimerState(timer.getBreakTimerState());
    }
}
