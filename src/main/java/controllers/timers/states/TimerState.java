package controllers.timers.states;

public interface TimerState {
    void start();

    void pause();

    void stop();

    void startBreakTime();
}