package com.cronutils.htime;

import com.cronutils.htime.jdk12.JDK12HDateTimeFormat;
import com.cronutils.htime.jodatime.JodaTimeHDateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
public class HDateTimeFormatBuilder {
    private static final HDateTimeFormatBuilder instance = new HDateTimeFormatBuilder();

    private HDateTimeFormatBuilder() {
    }

    public static HDateTimeFormatBuilder getInstance() {
        return instance;
    }

    public JodaTimeHDateTimeFormatBuilder forJodaTime() {
        return new JodaTimeHDateTimeFormatBuilder();
    }

    public JDK12TimeHDateTimeFormatBuilder forJDK12() {
        return new JDK12TimeHDateTimeFormatBuilder();
    }

    public static class JodaTimeHDateTimeFormatBuilder {
        public HDateTimeFormat<DateTimeFormatter> getFormatter() {
            return new JodaTimeHDateTimeFormat(Locale.US);
        }

        public HDateTimeFormat<DateTimeFormatter> getFormatter(Locale locale) {
            return new JodaTimeHDateTimeFormat(locale);
        }
    }

    public static class JDK12TimeHDateTimeFormatBuilder {
        public HDateTimeFormat<SimpleDateFormat> getFormatter() {
            return new JDK12HDateTimeFormat(Locale.US);
        }

        public HDateTimeFormat<SimpleDateFormat> getFormatter(Locale locale) {
            return new JDK12HDateTimeFormat(locale);
        }
    }
}
