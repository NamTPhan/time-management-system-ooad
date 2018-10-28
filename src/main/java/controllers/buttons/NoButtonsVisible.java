package controllers.buttons;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NoButtonsVisible implements ButtonBehavior {
    @FXML
    private Button playButton, pauseButton;

    @Override
    public void play() {
        playButton.setVisible(false);
        pauseButton.setVisible(false);
    }

    @Override
    public void pause() {
        // There are no buttons to click so this function will be run
        // after the session ends
    }

    @Override
    public void resetButtons() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
    }

    @Override
    public void playInBreak() {
        playButton.setVisible(false);
        pauseButton.setVisible(true);
    }

    @Override
    public void pauseInBreak() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
    }

    @Override
    public void setupFXMLReferences(Button playButton, Button pauseButton, Button stopButton) {
        this.playButton = playButton;
        this.pauseButton = pauseButton;
    }
}