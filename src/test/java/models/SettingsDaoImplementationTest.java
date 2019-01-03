package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SettingsDaoImplementationTest {

    private List<Settings> settingsArray = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        settingsArray.add(new Settings(1, 0, 1, 0, 12, 20, 0));
        settingsArray.add(new Settings(2, 1, 5, 1, 12, 20, 1));
        settingsArray.add(new Settings(3, 1, 5, 1, 5, 30, 2));
        settingsArray.add(new Settings(4, 0, 15, 0, 15, 23, 0));
        settingsArray.add(new Settings(5, 1, 10, 1, 8, 10, 1));
    }

    @Test
    public void getAllObjectsAndCheckType() {
        for (Object object: settingsArray) {
            assertThat(object, instanceOf(Settings.class));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getByIndexReplaceObjectWithNewOne() {
        Settings newConfig = new Settings(settingsArray.size() + 1,1,2,1,10,20,0);

        settingsArray.set(settingsArray.size(), newConfig);
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }
}