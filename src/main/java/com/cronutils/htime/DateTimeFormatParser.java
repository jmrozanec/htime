package com.cronutils.htime;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import java.util.Locale;
import java.util.Map;

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
public class DateTimeFormatParser {
    private DatetimeFormatterConstants constants;
    private Locale locale;
    private Map<String, String> dayOfWeek;
    private Map<String, String> months;
    private Map<String, String> dateFormatByCountry;

    public DateTimeFormatParser(DatetimeFormatterConstants constants, Locale locale) {
        this.constants = Validate.notNull(constants, "ConstantsConfiguration must not be null");
        this.locale = Validate.notNull(locale, "Locale must not be null");
        dayOfWeek = initDaysOfWeek(locale);
        months = initMonths(locale);
        dateFormatByCountry = initDateFormatByCountry();
    }

    public String parsePattern(String expression) {
        if (isTimezone(expression.replaceAll("[^A-Za-z0-9/_:\\-\\+ ]", ""))) {
            return timezonePattern(expression);
        }
        expression = expression.toLowerCase();
        if (expression.contains("/")) {
            return parseDateSlashes(expression);
        }
        if (expression.contains(":")) {
            return parseTimeWithColons(expression);
        }

        String clean = expression.replaceAll("[^A-Za-z0-9 ]", "");
        if (isNumberPattern(clean)) {
            return expression.replace(clean, numberPattern(clean));
        }
        if (dayOfWeek.containsKey(clean)) {
            return expression.replace(clean, dayOfWeek.get(clean));
        }
        if (months.containsKey(clean)) {
            return expression.replace(clean, months.get(clean));
        }

        return expression;
    }

    @VisibleForTesting
    String parseDateSlashes(String expression) {
        return dateFormatByCountry.containsKey(locale.getCountry()) ?
                dateFormatByCountry.get(locale.getCountry()) :
                String.format("%s%s/%s%s/%s%s", constants.dayOfMonth(), constants.dayOfMonth(),
                        constants.monthOfYear(), constants.monthOfYear(),
                        constants.year(), constants.year()
                );
    }

    @VisibleForTesting
    String parseTimeWithColons(String expression) {
        String hour = String.format("%s%s", constants.hourOfDay(), constants.hourOfDay());
        String pattern = "%s";
        if (expression.contains("am") || expression.contains("pm")) {
            hour = String.format("%s%s", constants.clockhourOfHalfday(), constants.clockhourOfHalfday());
            pattern = "%s " + constants.halfOfDay();
        }
        String[] parts = expression.split(":");
        if (parts.length == 2) {
            return String.format(pattern, String.format("%s:%s%s", hour, constants.minuteOfHour(), constants.minuteOfHour()));
        }
        return String.format(pattern,
                String.format("%s:%s%s:%s%s", hour,
                        constants.minuteOfHour(), constants.minuteOfHour(),
                        constants.secondOfMinute(), constants.secondOfMinute())
        );
    }

    @VisibleForTesting
    boolean isNumberPattern(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @VisibleForTesting
    String numberPattern(String string) {
        switch (string.length()) {
            case 4:
                return String.format("%s%s%s%s", constants.year(), constants.year(), constants.year(), constants.year());
            case 2:
                return String.format("%s%s", constants.dayOfMonth(), constants.dayOfMonth());
            default:
                return constants.dayOfMonth();
        }
    }

    @VisibleForTesting
    boolean isTimezone(String string) {
        try {
            DateTimeZone.forID(string);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @VisibleForTesting
    String timezonePattern(String string) {
        return constants.timezoneRFC822();
    }

    @VisibleForTesting
    Map<String, String> initDaysOfWeek(Locale locale) {
        String fullDoW =
                String.format("%s%s%s%s",
                        constants.dayOfWeekName(), constants.dayOfWeekName(),
                        constants.dayOfWeekName(), constants.dayOfWeekName()
                );
        LocalDate date = new LocalDate();
        Map<String, String> mapping = Maps.newHashMap();
        for (int j = 1; j < 8; j++) {
            String dow = date.withDayOfWeek(j).dayOfWeek().getAsText(locale).toLowerCase();
            mapping.put(dow, fullDoW);
            mapping.put(dow.substring(0, 3), constants.dayOfWeekName());
        }
        return mapping;
    }

    @VisibleForTesting
    Map<String, String> initMonths(Locale locale) {
        String fullMonth =
                String.format("%s%s%s%s",
                        constants.monthOfYear(), constants.monthOfYear(),
                        constants.monthOfYear(), constants.monthOfYear()
                );
        String shortMonth = String.format("%s%s%s", constants.monthOfYear(),
                constants.monthOfYear(), constants.monthOfYear());
        LocalDate date = new LocalDate();
        Map<String, String> mapping = Maps.newHashMap();
        for (int j = 1; j < 13; j++) {
            String moy = date.withMonthOfYear(j).monthOfYear().getAsText(locale).toLowerCase();
            mapping.put(moy, fullMonth);
            mapping.put(moy.substring(0, 3), shortMonth);
        }
        return mapping;
    }

    /**
     * Information taken from http://en.wikipedia.org/wiki/Date_format_by_country
     *
     * @return
     */
    @VisibleForTesting
    Map<String, String> initDateFormatByCountry() {
        String mmddyy = String.format("%s%s/%s%s/%s%s",
                constants.monthOfYear(), constants.monthOfYear(),
                constants.dayOfMonth(), constants.dayOfMonth(),
                constants.year(), constants.year()
        );
        String yymmdd = String.format("%s%s/%s%s/%s%s",
                constants.year(), constants.year(),
                constants.monthOfYear(), constants.monthOfYear(),
                constants.dayOfMonth(), constants.dayOfMonth()
        );
        Map<String, String> dateFormatByCountry = Maps.newHashMap();
        dateFormatByCountry.put("US", mmddyy);
        dateFormatByCountry.put("BZ", mmddyy);

        dateFormatByCountry.put("CN", yymmdd);
        dateFormatByCountry.put("KR", yymmdd);
        dateFormatByCountry.put("KP", yymmdd);
        dateFormatByCountry.put("TW", yymmdd);
        dateFormatByCountry.put("HU", yymmdd);
        dateFormatByCountry.put("IR", yymmdd);
        dateFormatByCountry.put("JP", yymmdd);
        dateFormatByCountry.put("LT", yymmdd);
        dateFormatByCountry.put("MN", yymmdd);
        return dateFormatByCountry;
    }
}
