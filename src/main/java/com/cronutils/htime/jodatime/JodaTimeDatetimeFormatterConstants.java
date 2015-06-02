package com.cronutils.htime.jodatime;

import com.cronutils.htime.DatetimeFormatterConstants;

/**
 * Implements constants from
 * http://joda-time.sourceforge.net/apidocs/org/joda/time/format/DateTimeFormat.html
 */
public class JodaTimeDatetimeFormatterConstants implements DatetimeFormatterConstants {

    public String era() {
        return "G";
    }

    public String centuryOfEra() {
        return "C";
    }

    public String yearOfEra() {
        return "Y";
    }

    public String weekyear() {
        return "x";
    }

    public String weekOfWeekyear() {
        return "w";
    }

    @Override
    public String weekOfMonth() {
        throw new RuntimeException("Not supported method: weekOfMonth");
    }

    @Override
    public String dayOfWeekInMonth() {
        throw new RuntimeException("Not supported method: dayOfWeekInMonth");
    }

    public String dayOfWeekNumber() {
        return "e";
    }

    public String dayOfWeekName() {
        return "E";
    }

    public String year() {
        return "y";
    }

    public String dayOfYear() {
        return "D";
    }

    public String monthOfYear() {
        return "M";
    }

    public String dayOfMonth() {
        return "d";
    }

    public String halfOfDay() {
        return "a";
    }

    public String hourOfHalfday() {
        return "K";
    }

    public String clockhourOfHalfday() {
        return "h";
    }

    public String hourOfDay() {
        return "H";
    }

    public String clockHourOfDay() {
        return "k";
    }

    public String minuteOfHour() {
        return "m";
    }

    public String secondOfMinute() {
        return "s";
    }

    public String fractionOfSecond() {
        return "S";
    }

    public String timezonePST() {
        return "z";
    }

    public String timezoneRFC822() {
        return "Z";
    }

    public String timezoneISO8601() {
        throw new RuntimeException("Not supported method: timezoneISO8601");
    }
}
