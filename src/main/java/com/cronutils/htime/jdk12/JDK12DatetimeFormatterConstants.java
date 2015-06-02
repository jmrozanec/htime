package com.cronutils.htime.jdk12;

/*
 * Copyright 2015 jmrozanec
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.cronutils.htime.DatetimeFormatterConstants;

/**
 * Implements mapping used from JDK 1.2 onward up to JDK7:
 * http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 */
public class JDK12DatetimeFormatterConstants implements DatetimeFormatterConstants {

    public String era() {
        return "G";
    }

    public String centuryOfEra() {
        throw new RuntimeException("Not supported method: weekOfMonth");
    }

    public String yearOfEra() {
        throw new RuntimeException("Not supported method: weekOfMonth");
    }

    public String weekyear() {
        return "Y";
    }

    public String weekOfWeekyear() {
        return "w";
    }

    @Override
    public String weekOfMonth() {
        return "W";
    }

    @Override
    public String dayOfWeekInMonth() {
        return "F";
    }

    public String dayOfWeekNumber() {
        return "u";
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

    @Override
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
        return "X";
    }

}
