package models;

import java.util.Calendar;
import java.util.Date;

public class Session {
    private Date date;
    private int investedTime; // In seconds

    /**
     * Constructs a session object
     */
    public Session() {
        this.date = new Date(); // Now
        this.investedTime = 0;  // Zero time invested
    }

    public void setInvestedTime(int seconds) {
        this.investedTime = seconds;
    }

    @Override
    public String toString() {
        return "Session: Date = " + this.date + ", invested time: " + this.investedTime + ".";
    }
}
