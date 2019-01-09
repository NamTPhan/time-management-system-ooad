package models;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SettingsDaoImplementationTest {

    private static List<Settings> settingsArray = new ArrayList<>();

    @BeforeClass
    public static void setUp() throws Exception {
        settingsArray.add(new Settings(1, 0, 1, 0, 12, 20, 0));
        settingsArray.add(new Settings(2, 1, 5, 1, 12, 20, 1));
        settingsArray.add(new Settings(3, 1, 5, 1, 5, 30, 2));
        settingsArray.add(new Settings(4, 0, 15, 0, 15, 23, 0));
        settingsArray.add(new Settings(5, 1, 10, 1, 8, 10, 1));

        for (Settings settings : settingsArray) {
            System.out.println(settings.toString());
        }
        System.out.println();
    }

    /**
     * Boundary condition CORRECT: Reference, check if every object is from the correct class
     */
    @Test
    public void getAllObjectsAndCheckType() {
        for (Object object: settingsArray) {
            assertThat(object, instanceOf(Settings.class));
        }
    }

    /**
     * Boundary condition CORRECT: Existence, forced error to check if an object exists
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getByIndexReplaceObjectWithNewOne() {
        Settings newConfig = new Settings(settingsArray.size() + 1,1,2,1,10,20,0);

        settingsArray.set(settingsArray.size(), newConfig);
    }

    @Test
    public void saveChangesSettingsConfig() {
        Settings[] newObjects = new Settings[2];
        newObjects[0] = new Settings(settingsArray.size() + 1, 1,1,1,10,15,2);
        newObjects[1] = new Settings(settingsArray.size() + 2, 1,1,1,10,15,2);

        for (int i = 0; i < newObjects.length; i++) {
            settingsArray.add(newObjects[i]);
        }

//        assertThat(settingsArray.get(settingsArray.size() - 1), equalTo(Settings.class));
//        assertThat(settingsArray.get(settingsArray.size() - 2), equalTo(Settings.class));
    }

    /**
     * Boundary condition CORRECT: Range, check if values has at least the minimum value
     */
    @Test
    public void updateChangesSettingsConfig() {

        Settings edit = settingsArray.get(2);
        edit.setRoundSize(6);
        edit.setLengthLongBreak(25);
        edit.setLengthShortBreak(15);
        edit.setSessionGoal(2);

        assertTrue(edit.getRoundSize() >= 1);
    }

//    @Test
//    public void delete() {
//    }
//
//    @Test
//    public void deleteAll() {
//    }

    @AfterClass
    public static void showChanges() {
        for (Settings settings : settingsArray) {
            System.out.println(settings.toString());
        }
    }
}