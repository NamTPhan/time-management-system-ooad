package controllers.buttons;

import javafx.scene.control.Button;

public interface ButtonBehavior {
    void play();
    void pause();
    void playInBreak();
    void pauseInBreak();
    void resetButtons();
    void setupFXMLReferences(Button playButton, Button pauseButton, Button stopButton);
}