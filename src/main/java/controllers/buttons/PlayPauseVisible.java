package controllers.buttons;

import controllers.buttons.ButtonVisibilityBehavior;
import controllers.timers.TimerController;
import javafx.scene.control.Button;

public class PlayPauseVisible implements ButtonVisibilityBehavior {
    private Button playButton = TimerController.playButton,
            pauseButton = TimerController.pauseButton;

    @Override
    public void turnButtonsOn() {
        if (playButton.isVisible()) {
            playButton.setVisible(false);
            pauseButton.setVisible(true);
        }
    }

    @Override
    public void turnButtonsOff() {
        if (pauseButton.isVisible()) {
            playButton.setVisible(true);
            pauseButton.setVisible(false);
        }
    }

    @Override
    public void sessionEndsResetButtons() {
        pauseButton.setVisible(false);
        playButton.setVisible(true);
    }
}
