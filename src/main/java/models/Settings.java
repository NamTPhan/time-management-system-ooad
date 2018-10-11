package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Settings implements GenericDAO<Settings> {

    private int settingsId, roundSize, sessionGoal, sound, lengthShortBreak, lengthLongBreak;
    private Settings databaseSettings;

    MyJDBC myJDBC = new MyJDBC("timemanagementooad");

    // Default settings values
    private static final int DEFAULT_SETTINGS_ID = 1,
            DEFAULT_ROUND_SIZE = 4,
            DEFAULT_SESSION_GOAL = 4,
            DEFAULT_SOUND = 0,
            DEFAULT_SHORT_BREAK = 5,
            DEFAULT_LONG_BREAK = 15;

    public Settings(int settingsId, int roundSize, int sessionGoal, int sound, int lengthShortBreak, int lengthLongBreak) {
        this.settingsId = settingsId;
        this.roundSize = roundSize;
        this.sessionGoal = sessionGoal;
        this.sound = sound;
        this.lengthShortBreak = lengthShortBreak;
        this.lengthLongBreak = lengthLongBreak;
    }

    public Settings() {
        this(DEFAULT_SETTINGS_ID, DEFAULT_ROUND_SIZE, DEFAULT_SESSION_GOAL, DEFAULT_SOUND, DEFAULT_SHORT_BREAK, DEFAULT_LONG_BREAK);
    }

    public Settings getCurrentSettings() {

        try {
            String query = "SELECT * FROM settings WHERE settingsId = 1;";
            ResultSet resultSet = myJDBC.executeResultSetQuery(query);
            while (resultSet.next()) {
                int settingsId = resultSet.getInt("settingsId");
                int dailyRoundSize = resultSet.getInt("dailyRoundSize");
                int dailySessionGoal = resultSet.getInt("dailySessionGoal");
                int sound = resultSet.getInt("sound");
                int lengthShortBreak = resultSet.getInt("lengthShortBreak");
                int lengthLongBreak = resultSet.getInt("lengthLongBreak");

                databaseSettings = new Settings(settingsId, dailyRoundSize, dailySessionGoal, sound, lengthShortBreak, lengthLongBreak);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        return  databaseSettings;
    }

    // Getters
    public int getSettingsId() {
        return settingsId;
    }

    public int getRoundSize() {
        return roundSize;
    }

    public int getSessionGoal() {
        return sessionGoal;
    }

    public int getSound() {
        return sound;
    }

    public int getLengthShortBreak() {
        return lengthShortBreak;
    }

    public int getLengthLongBreak() {
        return lengthLongBreak;
    }

    // Setters
    public void setRoundSize(int roundSize) {
        this.roundSize = roundSize;
    }

    public void setSessionGoal(int sessionGoal) {
        this.sessionGoal = sessionGoal;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public void setLengthShortBreak(int lengthShortBreak) {
        this.lengthShortBreak = lengthShortBreak;
    }

    public void setLengthLongBreak(int lengthLongBreak) {
        this.lengthLongBreak = lengthLongBreak;
    }

    @Override
    public List<Settings> getAll() {
        return null;
    }

    @Override
    public Settings getByIndex(int index) {
        return null;
    }

    @Override
    public void save(Settings settings) {

    }

    @Override
    public void update(int index, Settings settings) {

    }

    @Override
    public void delete(int index, Settings settings) {

    }

    @Override
    public void deleteAll() {

    }
}
