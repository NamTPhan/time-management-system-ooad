package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import models.Session;

public class PomodoroTimerController extends TimerController {

    public PomodoroTimerController() {
        super(25);
    }
}
