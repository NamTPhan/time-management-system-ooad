package controllers.timers;

import controllers.buttons.AllButtonsVisible;
import java.awt.*;

public class OtherTimerController extends TimerController {
    protected final int MIN_BREAK_TIME = 10,
            MAX_BREAK_TIME = 30;

    public OtherTimerController(int duration, int breakDuration) throws IndexOutOfBoundsException{
        super(duration, breakDuration, new AllButtonsVisible());
        setColors(new Color(152, 235, 163), new Color(171, 235, 230));

        if(duration > 60 || breakDuration > 20)
            throw new IndexOutOfBoundsException();
    }
}

