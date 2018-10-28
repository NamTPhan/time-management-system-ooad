package controllers.timers;

import controllers.buttons.AllButtonsVisible;

import java.awt.*;

public class HourTimerController extends TimerController {
    protected final int MIN_BREAK_TIME = 10,
                        MAX_BREAK_TIME = 30;
    
    public HourTimerController() {
        super(60, 15, new AllButtonsVisible());
        setColors(Color.getHSBColor(128, 35, 72), Color.getHSBColor(176, 27, 93));
    }
}

