package controllers.timers;

import controllers.buttons.NoButtonsVisible;

import java.awt.*;

public class TenMinuteTimerController extends TimerController {
    protected final int MIN_BREAK_TIME = 0,
                        MAX_BREAK_TIME = 5;

    public TenMinuteTimerController() {
        super(10, 5, new NoButtonsVisible());
        setColors(Color.getHSBColor(262, 22, 70), Color.getHSBColor(360, 22, 70));
    }
}