package controllers.timers;

import controllers.buttons.NoButtonsVisible;

import java.awt.*;

public class TenMinuteTimerController extends TimerController {

    public TenMinuteTimerController() {
        super(10, 0);
        timerColor = Color.getHSBColor(262, 22, 70);
        breakColor = Color.getHSBColor(360, 22, 70);
        buttonBehavior = new NoButtonsVisible();
    }
}