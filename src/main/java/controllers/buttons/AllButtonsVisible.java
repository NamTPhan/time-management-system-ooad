package controllers.buttons;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AllButtonsVisible implements ButtonBehavior {
    @FXML
    private Button playButton, pauseButton, stopButton;

    @Override
    public void play() {
        playButton.setVisible(false);
        pauseButton.setVisible(true);
        stopButton.setVisible(true);
    }

    @Override
    public void pause() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
        stopButton.setVisible(false);
    }

    @Override
    public void playInBreak() {
        playButton.setVisible(false);
        pauseButton.setVisible(true);
        stopButton.setVisible(false);
    }

    @Override
    public void pauseInBreak() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
        stopButton.setVisible(false);
    }

    @Override
    public void resetButtons() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
        stopButton.setVisible(false);
    }

    @Override
    public void setupFXMLReferences(Button playButton, Button pauseButton, Button stopButton) {
        this.playButton = playButton;
        this.pauseButton = pauseButton;
        this.stopButton = stopButton;
    }
}