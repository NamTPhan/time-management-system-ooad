package controllers.timers;

import controllers.buttons.AllButtonsVisible;

import java.awt.*;

public class HourTimerController extends TimerController {
    protected final int MIN_BREAK_TIME = 10,
                        MAX_BREAK_TIME = 30;
    
    public HourTimerController() {
        super(60, 15, new AllButtonsVisible());
        setColors(new Color(152, 235, 163), new Color(171, 235, 230));
    }
}

