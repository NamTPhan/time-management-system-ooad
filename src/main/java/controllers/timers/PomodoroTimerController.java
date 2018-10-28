package controllers.timers;

import controllers.buttons.PlayPauseVisible;
import java.awt.Color;

public class PomodoroTimerController extends TimerController {
    protected final int MIN_BREAK_TIME = 5,
                        MAX_BREAK_TIME = 20;

    public PomodoroTimerController() {
        super(25, 5, new PlayPauseVisible());
        setColors(Color.getHSBColor(0, 68, 66), Color.getHSBColor(206, 68, 66));
    }
}
