package controllers.timers;

import controllers.buttons.AllButtonsVisible;
import controllers.buttons.ButtonBehavior;
import controllers.timers.states.Timer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Session;

import java.awt.*;
import javafx.scene.layout.GridPane;

public abstract class TimerController {
    // Initialize JavaFX object references
    @FXML
    protected Button playButton, pauseButton, stopButton, settingsButton;
    @FXML
    protected Label timerLabel;
    @FXML
    protected GridPane timerBackground;

    private Session currentSession;
    private Color backgroundColorRunning, backgroundColorInBreak;
    private ButtonBehavior buttonBehavior;

    private final Timer timer;

    /**
     * This constructor is accessible from subclasses so users can create
     * subcontrollers that can use different timer durations
     * <p>
     * Constructs a object of TimerController data type
     *
     * @param duration      minutes in the timer
     * @param breakDuration minutes of a break after the timer finished
     */
    protected TimerController(int duration, int breakDuration) {
        this(duration, breakDuration, new AllButtonsVisible());
    }

    /**
     * This constructor is accessible from subclasses so users can create
     * subcontrollers that can use different timer durations
     * <p>
     * Constructs a object of TimerController data type
     *
     * @param duration       minutes in the timer
     * @param breakDuration  minutes of a break after the timer finished
     * @param buttonBehavior refers to a button behavior datatype instance
     */
    protected TimerController(int duration, int breakDuration, ButtonBehavior buttonBehavior) {
        this.currentSession = new Session();
        this.timer = new Timer(duration, breakDuration, this);
        this.buttonBehavior = buttonBehavior;
        this.backgroundColorRunning = Color.LIGHT_GRAY;
        this.backgroundColorInBreak = Color.BLUE;
    }

    /**
     * Set up the FXML object references
     */
    public void initialize() {
        playButton.setOnAction(event -> {
            startTimer();
        });
        pauseButton.setOnAction(event -> {
            pauseTimer();
        });
        stopButton.setOnAction(event -> {
            stopTimer();
        });
        settingsButton.setOnAction(event -> {
            openSettings();
        });

        timer.setupFXMLReferences(timerLabel);
        buttonBehavior.setupFXMLReferences(playButton, pauseButton, stopButton);
        timerBackground.setStyle("-fx-background-color: rgb" + convertColorToRGB(backgroundColorRunning));
    }

    /**
     * Start/resume a session
     */
    public void startTimer() {
        currentSession = (currentSession == null) ? new Session() : currentSession;

        // Change button behavior based on timer state
        if (timer.inBreakTime) buttonBehavior.playInBreak();
        else buttonBehavior.play();

        timer.start();
    }

    /**
     * Pauses the timer and save the time that is
     * already invested
     */
    public void pauseTimer() {
        timer.pause();

        if (timer.inBreakTime) {
            buttonBehavior.pauseInBreak();
        } else {
            buttonBehavior.pause();
            updateInvestedTime();
        }
    }

    /**
     * Stop the timer and resets the values
     */
    public void stopTimer() {
        timer.stop();

        buttonBehavior.resetButtons();
        updateInvestedTime();

        currentSession = new Session(); // Remove the reference to the object
    }

    /**
     * Take action based on the fact that the timer is in a break
     */
    public void enterBreakTime() {
        buttonBehavior.playInBreak();
        timerBackground.setStyle("-fx-background-color: rgb" + convertColorToRGB(backgroundColorInBreak));
    }
    
    /**
     * Take action based on the fact that the timer has finished the break time
     */
    public void finishBreakTime() {
        buttonBehavior.resetButtons();
        timerBackground.setStyle("-fx-background-color: rgb" + convertColorToRGB(backgroundColorRunning));
    }
    
    /**
     * Set the colors of the timer(view)
     *
     * @param bgWhileRunning Color of the background when the timer is running
     * @param bgInBreakTime  Color of the background when the timer is in a break state
     */
    public void setColors(Color bgWhileRunning, Color bgInBreakTime) {
        this.backgroundColorRunning = bgWhileRunning;
        this.backgroundColorInBreak = bgInBreakTime;
    }

    /**
     * Converts a Color datatype to RGB
     * @param colorToBeConverted
     * @return rgb variables between brackets
     */
    private String convertColorToRGB(Color colorToBeConverted) {
        String rgb = colorToBeConverted.getRed() 
                + ", " 
                + colorToBeConverted.getGreen() 
                + ", " 
                + colorToBeConverted.getBlue();
        
        return "(" + rgb + ")";
    } 
            
    /**
     * Opens the settings window
     */
    public void openSettings() {
        try {
            Stage stageCurrent = (Stage) settingsButton.getScene().getWindow();
            stageCurrent.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/settings.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Settings");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cannot load new window");
        }
    }

    /**
     * Calculates and updates the time that is invested in a task
     */
    private void updateInvestedTime() {
        currentSession.setInvestedTime(timer.getInvestedTime());
    }
}