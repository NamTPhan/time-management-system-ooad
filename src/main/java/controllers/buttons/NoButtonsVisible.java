package controllers.buttons;

import controllers.timers.TimerController;
import javafx.scene.control.Button;

public class NoButtonsVisible implements ButtonVisibilityBehavior {
    private Button playButton = TimerController.playButton,
            pauseButton = TimerController.pauseButton;

    @Override
    public void turnButtonsOn() {
        if (playButton.isVisible())
            playButton.setVisible(false);
    }

    @Override
    public void turnButtonsOff() {
        // There are no buttons to click so this function will be run
        // after the session ends
    }

    @Override
    public void sessionEndsResetButtons() {
        pauseButton.setVisible(false);
        playButton.setVisible(true);
    }
}
