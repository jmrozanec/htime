package com.cronutils.htime.jdk12;

import com.cronutils.htime.DateTimeFormatParser;
import com.cronutils.htime.HDateTimeFormat;
import org.apache.commons.lang3.Validate;

import java.text.SimpleDateFormat;
import java.util.Locale;

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
class Jdk12HDateTimeFormat implements HDateTimeFormat<SimpleDateFormat>{
    private Locale locale;

    public Jdk12HDateTimeFormat(Locale locale){
        Validate.notNull(locale, "Locale should not be null");
        this.locale = locale;
    }

    @Override
    public SimpleDateFormat forPattern(String expression) {
        Validate.notBlank(expression, "Expression must not be blank or null");
        DateTimeFormatParser parser = new DateTimeFormatParser(locale);
        expression = expression.replaceAll("\\s+", " ");
        expression = expression.replace(" AM", "AM").replace(" am", "am").replace(" PM", "PM").replace(" pm", "pm");
        String[]parts = expression.split(" ");
        StringBuilder builder = new StringBuilder();
        for(String part : parts){
            builder.append(String.format("%s ", parser.parsePattern(part)));
        }
        return new SimpleDateFormat(builder.toString().trim());
    }
}
