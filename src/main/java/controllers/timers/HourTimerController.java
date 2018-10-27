package controllers.timers;

import controllers.buttons.AllButtonsVisible;

import java.awt.*;

public class HourTimerController extends TimerController {

    public HourTimerController() {
        super(60, 15);
        backgroundColorWhenRunning = Color.getHSBColor(128, 35, 72);
        backgroundColorWhenInBreak = Color.getHSBColor(176, 27, 93);
        buttonBehavior = new AllButtonsVisible();
    }
}

