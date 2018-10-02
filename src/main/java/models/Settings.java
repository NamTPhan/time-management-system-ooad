package models;

public class Settings {

    private int roundSize, sessionGoal, sound, lengthShortBreak, lengthLongBreak;

    // Default settings values
    private static final int DEFAULT_ROUND_SIZE = 4,
            DEFAULT_SESSION_GOAL = 4,
            DEFAULT_SOUND = 0,
            DEFAULT_SHORT_BREAK = 5,
            DEFAULT_LONG_BREAK = 15;

    public Settings(int roundSize, int sessionGoal, int sound, int lengthShortBreak, int lengthLongBreak) {
        this.roundSize = roundSize;
        this.sessionGoal = sessionGoal;
        this.sound = sound;
        this.lengthShortBreak = lengthShortBreak;
        this.lengthLongBreak = lengthLongBreak;
    }

    public Settings() {
        this(DEFAULT_ROUND_SIZE, DEFAULT_SESSION_GOAL, DEFAULT_SOUND, DEFAULT_SHORT_BREAK, DEFAULT_LONG_BREAK);
    }
}
