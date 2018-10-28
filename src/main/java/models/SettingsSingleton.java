package models;

public class SettingsSingleton {

    private int settingsId, roundSize, sessionGoal, sound, lengthShortBreak, lengthLongBreak, timerType;
    private static SettingsSingleton settingsInstance;
    private boolean saveStatus = false;
    private boolean updateStatus = false;

    // Default settings values
    private static final int DEFAULT_SETTINGS_ID = 1,
            DEFAULT_ROUND_SIZE = 0,
            DEFAULT_SESSION_GOAL = 4,
            DEFAULT_SOUND = 0,
            DEFAULT_SHORT_BREAK = 5,
            DEFAULT_LONG_BREAK = 15,
            DEFAULT_TIMER_TYPE = 0;

    private SettingsSingleton() {
//        if (settingsInstance.isSaveStatus() == false && settingsInstance.isUpdateStatus() == false) {
//            settingsInstance.setSettingsId(DEFAULT_SETTINGS_ID);
//            settingsInstance.setRoundSize(DEFAULT_ROUND_SIZE);
//            settingsInstance.setSessionGoal(DEFAULT_SESSION_GOAL);
//            settingsInstance.setSound(DEFAULT_SOUND);
//            settingsInstance.setLengthShortBreak(DEFAULT_SHORT_BREAK);
//            settingsInstance.setLengthLongBreak(DEFAULT_LONG_BREAK);
//            settingsInstance.setTimerType(DEFAULT_TIMER_TYPE);
//        }
    }

    public static SettingsSingleton getInstance() {
        if (settingsInstance == null) {
            settingsInstance = new SettingsSingleton();
        } else if (settingsInstance.isSaveStatus() || settingsInstance.isUpdateStatus()) {
            settingsInstance.getSettingsId();
            settingsInstance.getRoundSize();
            settingsInstance.getSessionGoal();
            settingsInstance.getSound();
            settingsInstance.getLengthShortBreak();
            settingsInstance.getLengthLongBreak();
            settingsInstance.getTimerType();
        }

        return settingsInstance;
    }

    public boolean isSaveStatus() {
        return saveStatus;
    }

    public void setSaveStatus(boolean saveStatus) {
        this.saveStatus = saveStatus;
    }

    public boolean isUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(boolean updateStatus) {
        this.updateStatus = updateStatus;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public int getRoundSize() {
        return roundSize;
    }

    public void setRoundSize(int roundSize) {
        this.roundSize = roundSize;
    }

    public int getSessionGoal() {
        return sessionGoal;
    }

    public void setSessionGoal(int sessionGoal) {
        this.sessionGoal = sessionGoal;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public int getLengthShortBreak() {
        return lengthShortBreak;
    }

    public void setLengthShortBreak(int lengthShortBreak) {
        this.lengthShortBreak = lengthShortBreak;
    }

    public int getLengthLongBreak() {
        return lengthLongBreak;
    }

    public void setLengthLongBreak(int lengthLongBreak) {
        this.lengthLongBreak = lengthLongBreak;
    }

    public int getTimerType() {
        return timerType;
    }

    public void setTimerType(int timerType) {
        this.timerType = timerType;
    }
}
