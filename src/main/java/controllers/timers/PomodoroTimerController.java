package controllers.timers;

import controllers.buttons.PlayPauseVisible;
import java.awt.Color;

public class PomodoroTimerController extends TimerController {
    protected final int MIN_BREAK_TIME = 5,
                        MAX_BREAK_TIME = 20;

    public PomodoroTimerController() {
        super(25, 5, new PlayPauseVisible());
        setColors(new Color(230, 73, 73), new Color(73, 162, 230));
    }
}
