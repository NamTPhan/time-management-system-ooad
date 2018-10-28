package controllers.timers;

import controllers.buttons.AllButtonsVisible;
import controllers.buttons.ButtonBehavior;
import controllers.timers.states.Timer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.Session;

import java.awt.*;

public abstract class TimerController {
    // Initialize JavaFX object references
    @FXML
    protected Button playButton, pauseButton, stopButton;
    @FXML
    protected Label timerLabel;
    
    private Session currentSession;
    private Color backgroundColorWhenRunning, backgroundColorWhenInBreak;
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
        this.backgroundColorWhenRunning = Color.LIGHT_GRAY;
        this.backgroundColorWhenInBreak = Color.BLUE;
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
        
        timer.setupFXMLReferences(timerLabel);
        buttonBehavior.setupFXMLReferences(playButton, pauseButton, stopButton);
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
    }
    
    /**
     * Take action based on the fact that the timer has finished the break time
     */
    public void finishBreakTime() {
        buttonBehavior.resetButtons();
    }

    /**
     * Set the colors of the timer(view)
     * @param bgWhileRunning Color of the background when the timer is running
     * @param bgInBreakTime  Color of the background when the timer is in a break state
     */
    public void setColors(Color bgWhileRunning, Color bgInBreakTime){
        this.backgroundColorWhenRunning = bgWhileRunning;
        this.backgroundColorWhenInBreak = bgInBreakTime;
    }
    
    /**
     * Calculates and updates the time that is invested in a task
     */
    private void updateInvestedTime() {
        currentSession.setInvestedTime(timer.getInvestedTime());
    }
}