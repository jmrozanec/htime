package com.cronutils.htime;

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
public interface DatetimeFormatterConstants {

    String era();

    String centuryOfEra();

    String yearOfEra();

    String weekyear();

    String weekOfWeekyear();

    String weekOfMonth();

    String dayOfWeekInMonth();

    String dayOfWeekNumber();

    String dayOfWeekName();

    String year();

    String dayOfYear();

    String monthOfYear();

    String dayOfMonth();

    String halfOfDay();

    String hourOfHalfday();

    String clockhourOfHalfday();

    String hourOfDay();

    String clockHourOfDay();

    String minuteOfHour();

    String secondOfMinute();

    String fractionOfSecond();

    String timezonePST();

    String timezoneRFC822();

    String timezoneISO8601();
}
