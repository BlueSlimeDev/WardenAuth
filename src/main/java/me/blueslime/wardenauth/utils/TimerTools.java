package me.blueslime.wardenauth.utils;

import java.util.concurrent.TimeUnit;

public class TimerTools {

    private final TimeUnit unit;

    private final int time;

    public TimerTools(TimeUnit unit, int time) {
        this.unit = unit;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public static TimerTools fromText(String text) {
        TimeUnit unit = null;
        int time;

        if (text.contains("[SEC]") || text.contains("[SECONDS]")) {
            unit = TimeUnit.SECONDS;
        }
        if (text.contains("[MIN]") || text.contains("[MINUTES]")) {
            unit = TimeUnit.MINUTES;
        }
        if (text.contains("[HRS]") || text.contains("[HOURS]")) {
            unit = TimeUnit.HOURS;
        }
        if (unit == null) {
            unit = TimeUnit.SECONDS;
        }

        text = text.replace("[MIN]", "")
                .replace("[MINUTES]", "")
                .replace("[HRS]", "")
                .replace("[HOURS]", "")
                .replace("[SEC]", "")
                .replace("[SECONDS]", "");

        time = Integer.parseInt(text);

        return new TimerTools(unit, time);
    }

}
