package controllers.timers;

import controllers.buttons.AllButtonsVisible;

import java.awt.*;

public class HourTimerController extends TimerController {

    public HourTimerController() {
        super(60, 15);
        timerColor = Color.getHSBColor(128, 35, 72);
        breakColor = Color.getHSBColor(176, 27, 93);
        buttonBehavior = new AllButtonsVisible();
    }
}

