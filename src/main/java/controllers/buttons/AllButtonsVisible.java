package controllers.buttons;

import controllers.timers.TimerController;
import javafx.scene.control.Button;

public class AllButtonsVisible implements ButtonVisibilityBehavior {
    private Button playButton = TimerController.playButton,
            pauseButton = TimerController.pauseButton,
            stopButton = TimerController.stopButton;

    @Override
    public void turnButtonsOn() {
        if (playButton.isVisible()) {           // Hide play button, show pause and stop button
            playButton.setVisible(false);
            pauseButton.setVisible(true);
            stopButton.setVisible(true);
        }
    }

    @Override
    public void turnButtonsOff() {
        if (pauseButton.isVisible()) {   // Hide pause and stop button, show play button
            playButton.setVisible(true);
            pauseButton.setVisible(false);
            stopButton.setVisible(false);
        }
    }

    @Override
    public void sessionEndsResetButtons() {
        pauseButton.setVisible(false);
        stopButton.setVisible(false);
        playButton.setVisible(true);
    }
}
