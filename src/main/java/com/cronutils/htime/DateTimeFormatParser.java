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
    private Locale locale;
    private Map<String, String> dayOfWeek;
    private Map<String, String> months;
    private Map<String, String> dateFormatByCountry;

    public DateTimeFormatParser(Locale locale){
        this.locale = Validate.notNull(locale, "Locale must not be null");
        dayOfWeek = initDaysOfWeek(locale);
        months = initMonths(locale);
        dateFormatByCountry = initDateFormatByCountry();
    }

    public String parsePattern(String expression){
        if(isTimezone(expression.replaceAll("[^A-Za-z0-9/_:\\-\\+ ]", ""))){
            return timezonePattern(expression);
        }
        expression = expression.toLowerCase();
        if(expression.contains("/")){
            return parseDateSlashes(expression);
        }
        if(expression.contains(":")){
            return parseTimeWithColons(expression);
        }

        String clean = expression.replaceAll("[^A-Za-z0-9 ]", "");
        if(isNumberPattern(clean)){
            return expression.replace(clean, numberPattern(clean));
        }
        if(dayOfWeek.containsKey(clean)){
            return expression.replace(clean, dayOfWeek.get(clean));
        }
        if(months.containsKey(clean)){
            return expression.replace(clean, months.get(clean));
        }

        return expression;
    }

    @VisibleForTesting
    String parseDateSlashes(String expression){
        return dateFormatByCountry.containsKey(locale.getCountry()) ?
                dateFormatByCountry.get(locale.getCountry()) : "dd/MM/YY";
    }

    @VisibleForTesting
    String parseTimeWithColons(String expression){
        String hour = "HH";
        String pattern = "%s";
        if(expression.contains("am") || expression.contains("pm")){
            hour = "hh";
            pattern = "%s a";
        }
        String [] parts = expression.split(":");
        if(parts.length==2){
            return String.format(pattern, String.format("%s:mm", hour));
        }
        return String.format(pattern, String.format("%s:mm:ss", hour));
    }

    @VisibleForTesting
    boolean isNumberPattern(String string){
        try{
            Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @VisibleForTesting
    String numberPattern(String string){
        switch (string.length()){
            case 4:
                return "YYYY";
            case 2:
                return "dd";
            default:
                return "d";
        }
    }

    @VisibleForTesting
    boolean isTimezone(String string){
        try{
            DateTimeZone.forID(string);
        }catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }

    @VisibleForTesting
    String timezonePattern(String string){
        return "Z";
    }

    @VisibleForTesting
    Map<String, String> initDaysOfWeek(Locale locale){
        LocalDate date = new LocalDate();
        Map<String, String> mapping = Maps.newHashMap();
        for(int j=1;j<8;j++){
            String dow = date.withDayOfWeek(j).dayOfWeek().getAsText(locale).toLowerCase();
            mapping.put(dow, "EEEE");
            mapping.put(dow.substring(0,3), "E");
        }
        return mapping;
    }

    @VisibleForTesting
    Map<String, String> initMonths(Locale locale){
        LocalDate date = new LocalDate();
        Map<String, String> mapping = Maps.newHashMap();
        for(int j=1;j<13;j++){
            String moy = date.withMonthOfYear(j).monthOfYear().getAsText(locale).toLowerCase();
            mapping.put(moy, "MMMM");
            mapping.put(moy.substring(0,3), "MMM");
        }
        return mapping;
    }

    /**
     * Information taken from http://en.wikipedia.org/wiki/Date_format_by_country
     * @return
     */
    @VisibleForTesting
    Map<String, String> initDateFormatByCountry(){
        Map<String, String> dateFormatByCountry = Maps.newHashMap();
        dateFormatByCountry.put("US", "MM/dd/YY");
        dateFormatByCountry.put("BZ", "MM/dd/YY");

        dateFormatByCountry.put("CN", "YY/MM/dd");
        dateFormatByCountry.put("KR", "YY/MM/dd");
        dateFormatByCountry.put("KP", "YY/MM/dd");
        dateFormatByCountry.put("TW", "YY/MM/dd");
        dateFormatByCountry.put("HU", "YY/MM/dd");
        dateFormatByCountry.put("IR", "YY/MM/dd");
        dateFormatByCountry.put("JP", "YY/MM/dd");
        dateFormatByCountry.put("LT", "YY/MM/dd");
        dateFormatByCountry.put("MN", "YY/MM/dd");
        return dateFormatByCountry;
    }
}
