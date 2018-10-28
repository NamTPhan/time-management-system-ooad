package controllers.buttons;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayPauseVisible implements ButtonBehavior {
    @FXML
    private Button playButton, pauseButton;
    
    @Override
    public void play() {
        playButton.setVisible(false);
        pauseButton.setVisible(true);
    }

    @Override
    public void pause() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
    }

    @Override
    public void playInBreak() {
        play();
    }

    @Override
    public void pauseInBreak() {
        pause();
    }

    @Override
    public void resetButtons() {
        playButton.setVisible(true);
        pauseButton.setVisible(false);
    }

    @Override
    public void setupFXMLReferences(Button playButton, Button pauseButton, Button stopButton) {
        this.playButton = playButton;
        this.pauseButton = pauseButton;
    }
}