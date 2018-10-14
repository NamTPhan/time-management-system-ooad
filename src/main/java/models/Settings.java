package models;

public class Settings {

    private int settingsId, roundSize, sessionGoal, sound, lengthShortBreak, lengthLongBreak;

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

}
