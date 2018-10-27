package controllers.timers;

import controllers.buttons.NoButtonsVisible;

import java.awt.*;

public class TenMinuteTimerController extends TimerController {

    public TenMinuteTimerController() {
        super(10, 0);
        backgroundColorWhenRunning = Color.getHSBColor(262, 22, 70);
        backgroundColorWhenInBreak = Color.getHSBColor(360, 22, 70);
        buttonBehavior = new NoButtonsVisible();
    }
}