package models;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SettingsTest {

    private String totalRounds;
    private static final int MAX_SETTINGS_ARRAY = 10;
    private Settings[] settingsArray;
    private Settings[] settingsArrayTemp;

    @Before
    public void setUp() throws Exception {
        settingsArray = new Settings[MAX_SETTINGS_ARRAY];
        settingsArray[0] = new Settings(1, 0, 1, 0, 12, 20, 0);
        settingsArray[1] = new Settings(2, 1, 5, 1, 12, 20, 1);
        settingsArray[2] = new Settings(3, 1, 5, 1, 5, 30, 2);
        settingsArray[3] = new Settings(4, 0, 15, 0, 15, 23, 0);
        settingsArray[4] = new Settings(5, 1, 10, 1, 8, 10, 1);

        settingsArrayTemp = new Settings[settingsArray.length];
        for (int i = 0; i < settingsArray.length; i++) {
            settingsArrayTemp[i] = settingsArray[i];
        }
    }

    /**
     * The user interface dropdown for setting the round size works on the method like 0 = 2 rounds, 1 = 4 rounds.
     * That means if you select the first option the round size variabele will get the value 0,
     * which will be saved in the database.
     * <p>
     * This test will check if the round size text ends with 'rounds'
     */
    @Test
    public void checkValidRoundSizeText() {
        // A switch is needed to select the correct round size text
        switch (settingsArray[0].getRoundSize()) {
            case 0:
                totalRounds = "2 rounds";
                break;
            case 1:
                totalRounds = "4 rounds";
                break;
        }
        assertThat(totalRounds, endsWith("rounds"));
    }

    @Test
    public void checkTypeOfObjectFromArray() {
        Object object = settingsArray[2];
        assertThat(object.getClass(), equalTo(Settings.class));
    }

    @Test
    public void compareObjectsLengthShortBreak() {
        Integer settingsObjectOne = settingsArray[0].getLengthShortBreak();
        Integer settingsObjectTwo = settingsArray[1].getLengthShortBreak();

        assertSame(settingsObjectOne, settingsObjectTwo);
    }

    @Test
    public void checkNegativeValueSessionGoal() {
        // Set session goal as a negative number
        settingsArray[4].setSessionGoal(-1);
        // Get session goal value
        int sessionGoal = settingsArray[4].getSessionGoal();

        assertFalse(sessionGoal > 0);
    }

    @Test
    public void checkRangeLengthLongBreak() {
        Settings settings = settingsArray[3];

        assertTrue(settings.getLengthLongBreak() >= 15 && settings.getLengthLongBreak() <= 30);
    }

    @Test
    public void compareTwoSettingsConfigShortBreaks() {
        assertNotSame(settingsArray[3].getLengthShortBreak(), settingsArray[4].getLengthShortBreak());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getSettingsFromArray() {
        settingsArray[0].setTimerType(1);
        assertNotSame(0, settingsArray[0].getTimerType());

        settingsArray[50].setTimerType(2);
    }

    @Test
    public void accessNonExistingObject() {
        assertThat(settingsArray[8], is(nullValue()));
    }

    @After
    public void resetArray() {
        settingsArray = settingsArrayTemp;
    }
}