package controllers.timers;

import controllers.buttons.PlayPauseVisible;

public class PomodoroTimerController extends TimerController {

    public PomodoroTimerController() {
        super(25, 5);
        buttonBehavior = new PlayPauseVisible();
    }
}
