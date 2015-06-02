package com.cronutils.htime.jodatime;

import com.cronutils.htime.DateTimeFormatParser;
import com.cronutils.htime.HDateTimeFormat;
import org.apache.commons.lang3.Validate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class JodaTimeHDateTimeFormat implements HDateTimeFormat<DateTimeFormatter> {
    private Locale locale;

    public JodaTimeHDateTimeFormat(Locale locale){
        Validate.notNull(locale, "Locale should not be null");
        this.locale = locale;
    }

    public DateTimeFormatter forPattern(String expression){
        Validate.notBlank(expression, "Expression must not be blank or null");
        DateTimeFormatParser parser = new DateTimeFormatParser(locale);
        expression = expression.replaceAll("\\s+", " ");
        expression = expression.replace(" AM", "AM").replace(" am", "am").replace(" PM", "PM").replace(" pm", "pm");
        String[]parts = expression.split(" ");
        StringBuilder builder = new StringBuilder();
        for(String part : parts){
            builder.append(String.format("%s ", parser.parsePattern(part)));
        }
        return DateTimeFormat.forPattern(builder.toString().trim());
    }
}
