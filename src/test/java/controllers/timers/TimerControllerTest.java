package controllers.timers;

import controllers.timers.states.IdleTimerState;
import controllers.timers.states.PauseTimerState;
import controllers.timers.states.RunningTimerState;
import models.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TimerControllerTest  {
    // Initialize timer array
    TimerController timerControllers[] = new TimerController[3];
    HourTimerController hourController;

    @Before
    public void setupTimers(){
        // Declare single timer
        hourController = new HourTimerController();

        // Declare array of timers
        timerControllers[0] = new HourTimerController();
        timerControllers[1] = new PomodoroTimerController();
        timerControllers[2] = new TenMinuteTimerController();
    }

    @Test
    public void correctBackgroundOnEveryTimer(){
        Color backgroundOfTimer, backgroundOfTimerInBreak;

        for (TimerController controller: timerControllers) {
            backgroundOfTimer = controller.getBackgroundColor();
            backgroundOfTimerInBreak = controller.getBackgroundColorInBreak();

            if (controller instanceof HourTimerController) {
                assertEquals(backgroundOfTimer, new Color(152, 235, 163));
                assertEquals(backgroundOfTimerInBreak, new Color(171, 235, 230));
            } else if (controller instanceof PomodoroTimerController) {
                assertEquals(backgroundOfTimer, new Color(230, 73, 73));
                assertEquals(backgroundOfTimerInBreak, new Color(73, 162, 230));
            } else if (controller instanceof TenMinuteTimerController) {
                assertEquals(backgroundOfTimer, new Color(193, 175, 224));
                assertEquals(backgroundOfTimerInBreak, new Color(235, 183, 184));
            }
        }
    }

    @Test
    public void javaFXElementsAreInitialized(){
        // JavaFX elements are not suppose to be created when
        // testing the application through a junit test.
        assertNull(hourController.timerLabel);
        assertNull(hourController.playButton);
        assertNull(hourController.pauseButton);
        assertNull(hourController.stopButton);
        assertNull(hourController.settingsButton);
        assertNull(hourController.timerBackground);
    }

    @Test
    public void checkChangeInSessionsInvestedTime() throws InterruptedException {
        // Make sure the session is new
        assertThat(hourController.getSession().getInvestedTime(), is(0));

        hourController.startTimer();
        Thread.sleep(10);     // Wait 1.2 miliseconds
        hourController.pauseTimer();

        assertThat(hourController.getSession().getInvestedTime(), greaterThan(0));
    }

    @Test
    public void createNewSessionAfterTimerStopped(){
        // At this stage, the timer suppose to be filled
        assertNotNull(hourController.getSession());
        hourController.startTimer();

        // Check if the session is replaced by a new one
        // after a timer has been stopped.
        Session oldSession = hourController.getSession();
        hourController.stopTimer();
        assertNotSame(oldSession, hourController.getCurrentSession());

    }

    @Test
    public void sessionResumesAfterResumingTimer(){
        Session oldSession = hourController.getSession();

        // Action: pause and play the already running timer
        hourController.pauseTimer();
        hourController.startTimer();

        // Check if session is still the same
        assertSame(oldSession, hourController.getCurrentSession());
    }

    @Test
    public void timerBeginsAtTheCorrectTime(){
        for (TimerController controller: timerControllers) {
                if (controller instanceof HourTimerController)
                    assertThat(controller.getTimer().getTimerMinutes(), is(equalTo(60)));
                else if (controller instanceof PomodoroTimerController)
                    assertThat(controller.getTimer().getTimerMinutes(), is(equalTo(25)));
                else if (controller instanceof TenMinuteTimerController)
                    assertThat(controller.getTimer().getTimerMinutes(), is(equalTo(10)));

            assertSame(controller.getTimer().getTimerSeconds(), 0);
        }
    }

    @Test
    public void checkStartingStateOfEachController(){
        // Test each timer if they are able to start correctly
        for (TimerController controller: timerControllers) {
            controller.startTimer();
            assertTrue(controller.getTimer().getTimerState() instanceof RunningTimerState);
        }
    }


    @Test
    public void checkCorrectBehaviorOfHourTimer(){
        TimerController controller = new HourTimerController();

        controller.startTimer();

        // Timer can be paused
        controller.pauseTimer();
        assertTrue(controller.getTimer().getTimerState() instanceof PauseTimerState);

        // Timer can be stopped
        controller.startTimer();
        controller.stopTimer();
        assertTrue(controller.getTimer().getTimerState() instanceof IdleTimerState);
    }

    @Test
    public void checkCorrectBehaviorOfPomodoroTimer(){
        TimerController controller = new PomodoroTimerController();

        controller.startTimer();

        // Timer can be paused
        controller.pauseTimer();
        assertTrue(controller.getTimer().getTimerState() instanceof PauseTimerState);

        // Timer cannot be stopped
        controller.startTimer();
        controller.stopTimer();     // Suppose to have no effect
        assertTrue(controller.getTimer().getTimerState() instanceof RunningTimerState);
    }

    @Test
    public void checkCorrectBehaviorOfTenMinuteTimer(){
        TimerController controller = new TenMinuteTimerController();

        // Ten minute timer can be played
        controller.startTimer();

        // Ten minute timer cannot be paused
        controller.pauseTimer();    // Suppose to have no effect
        assertTrue(controller.getTimer().getTimerState() instanceof RunningTimerState);
    }

    @After
    public void resetTimers(){
        // Reset the timers by setting the timers up again
        setupTimers();
    }
}