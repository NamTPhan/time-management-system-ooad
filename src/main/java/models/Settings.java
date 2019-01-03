package models;

/**
 * @author Nam Phan - 500769669
 */
public class Settings {

    private int settingsId, roundSize, sessionGoal, sound, lengthShortBreak, lengthLongBreak, timerType;
    private String totalRounds;

    // Default settings values
    private static final int DEFAULT_SETTINGS_ID = 1,
            DEFAULT_ROUND_SIZE = 0,
            DEFAULT_SESSION_GOAL = 4,
            DEFAULT_SOUND = 0,
            DEFAULT_SHORT_BREAK = 5,
            DEFAULT_LONG_BREAK = 15,
            DEFAULT_TIMER_TYPE = 0;

    public Settings(int settingsId, int roundSize, int sessionGoal, int sound, int lengthShortBreak,
                    int lengthLongBreak, int timerType) {
        this.settingsId = settingsId;
        this.roundSize = roundSize;
        this.sessionGoal = sessionGoal;
        this.sound = sound;
        this.lengthShortBreak = lengthShortBreak;
        this.lengthLongBreak = lengthLongBreak;
        this.timerType = timerType;
    }

    /**
     * Constructor used for insert to database without id, because of auto increment
     */
    public Settings(int roundSize, int sessionGoal, int sound, int lengthShortBreak,
                    int lengthLongBreak, int timerType) {
        this.roundSize = roundSize;
        this.sessionGoal = sessionGoal;
        this.sound = sound;
        this.lengthShortBreak = lengthShortBreak;
        this.lengthLongBreak = lengthLongBreak;
        this.timerType = timerType;
    }

    /**
     * Default constructor with all default values
     */
    public Settings() {
        this(DEFAULT_SETTINGS_ID, DEFAULT_ROUND_SIZE, DEFAULT_SESSION_GOAL, DEFAULT_SOUND, DEFAULT_SHORT_BREAK,
                DEFAULT_LONG_BREAK, DEFAULT_TIMER_TYPE);
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

    public int getTimerType() {
        return timerType;
    }

    // Setters
    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

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

    public void setTimerType(int timerType) {
        this.timerType = timerType;
    }

    public boolean checkTimerType() {
        if (this.timerType == 0 || this.timerType == 1 || this.timerType == 2) {
            return true;
        }
        return false;
    }

    public String checkRoundText() {
        switch (this.roundSize) {
            case 0:
                totalRounds = "2 rounds";
                break;
            case 1:
                totalRounds = "4 rounds";
                break;
        }
        return totalRounds;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "settingsId=" + settingsId +
                ", roundSize=" + roundSize +
                ", sessionGoal=" + sessionGoal +
                ", sound=" + sound +
                ", lengthShortBreak=" + lengthShortBreak +
                ", lengthLongBreak=" + lengthLongBreak +
                ", timerType=" + timerType +
                '}';
    }
}
