package models;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SettingsTest {

    private static List<Settings> settingsArray;

    @BeforeClass
    public static void setUp() throws Exception {
        settingsArray = new ArrayList<>();
        settingsArray.add(new Settings(1, 0, 1, 0, 12, 20, 0));
        settingsArray.add(new Settings(2, 1, 5, 1, 12, 20, 1));
        settingsArray.add(new Settings(3, 1, 5, 1, 5, 30, 2));
        settingsArray.add(new Settings(4, 0, 15, 0, 15, 23, 0));
        settingsArray.add(new Settings(5, 1, 10, 1, 8, 10, 1));

        for (Settings settings : settingsArray) {
            System.out.println(settings.toString());
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
    public void checkValidRoundSizeTextForDatabase() {
        Settings settings = new Settings();
        settingsArray.add(settings);

        String roundSizeText = settingsArray.get(settingsArray.size() - 1).checkRoundText();

        assertThat(roundSizeText, endsWith("rounds"));
    }

    @Test
    public void checkTypeOfObjectFromList() {
        Settings newObject = new Settings();
        settingsArray.add(newObject);

        Object object = settingsArray.get(settingsArray.size() - 1);
        assertThat(object.getClass(), equalTo(Settings.class));
    }

    @Test
    public void compareObjectsLengthShortBreak() {
        Integer settingsObjectOne = settingsArray.get(0).getLengthShortBreak();
        Integer settingsObjectTwo = settingsArray.get(1).getLengthShortBreak();

        // Double check
        assertTrue(settingsObjectOne == settingsObjectTwo);
        assertSame(settingsObjectOne, settingsObjectTwo);
    }

    @Test
    public void checkNegativeValueSessionGoal() {
        // Set session goal as a negative number
        settingsArray.get(4).setSessionGoal(-1);
        // Get session goal value
        int sessionGoal = settingsArray.get(4).getSessionGoal();

        assertFalse(sessionGoal > 0);
    }

    @Test
    public void checkRangeLengthLongBreak() {
        Settings settings = settingsArray.get(3);

        int lengthLongBreak = settings.getLengthLongBreak();

        assertTrue(lengthLongBreak >= 15 && lengthLongBreak <= 30);
    }

    @Test
    public void compareTwoSettingsConfigShortBreaks() {
        Object objectOne = settingsArray.get(3);
        Object objectTwo = settingsArray.get(4);

        int objectOneShortBreak = ((Settings) objectOne).getLengthShortBreak();
        int objectTwoShortBreak = ((Settings) objectTwo).getLengthShortBreak();

        assertNotSame(objectOneShortBreak, objectTwoShortBreak);
    }

    /**
     * Forced error: Set timer type at an object on place 50, but the array length is max 10
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void setTimerTypeForInvalidSettings() {
        Settings settingsConfigOne = settingsArray.get(0);
        settingsConfigOne.setTimerType(1);

        Settings settingsConfigTwo = settingsArray.get(1);
        settingsConfigTwo.setTimerType(0);

        assertNotSame(settingsConfigOne.getTimerType(), settingsConfigTwo.getTimerType());

        settingsArray.get(500).setTimerType(2);
        System.out.println(settingsArray.get(500).toString());
    }

    @Test
    public void accessNonExistingObject() {
        Settings settingsNull = new Settings();
        settingsArray.add(settingsNull);

        settingsNull = null;
        settingsArray.set(settingsArray.size() - 1, settingsNull);

        assertThat(settingsArray.get(settingsArray.size() - 1), is(nullValue()));

        settingsArray.remove(settingsArray.size() - 1);
    }

    @Test
    public void validationTimerStatus() {
        // Use default settings configurations
        Settings settings = new Settings();

        settings.setTimerType(4);

        assertThat(true, not(settings.checkTimerType()));
    }

    @Test
    public void checkMaximumLongBreak() {
        for (Settings settings: settingsArray) {
            assertTrue(settings.checkMaxValueLongBreak());
        }
    }

    /**
     * Set all settings configurations to default and
     * check if the most recent added settings is equal to the rest
     */
    @AfterClass
    public static void ensureSettingsInvariant() {
        // Current list
        for (Settings settings: settingsArray) {
            settings.setTimerType(0);
        }

        Settings recentSettings = new Settings(settingsArray.size() + 1,1,2,1,10,15,0);
        settingsArray.add(recentSettings);

        for (Settings settings: settingsArray) {
            assertTrue(settings.getTimerType() == recentSettings.getTimerType());
        }
    }
}