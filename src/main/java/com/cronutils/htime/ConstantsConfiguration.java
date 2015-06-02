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
class ConstantsConfiguration {

    public String eraDesignator(){
        return "G";
    }

    public String year(){
        return "y";
    }

    public String weekYear(){
        return "Y";
    }

    public String monthOfYear(){
        return "M";
    }

    public String weekOfYear(){
        return "w";
    }

    public String weekInMonth(){
        return "W";
    }

    public String dayInYear(){
        return "D";
    }

    public String dayOfMonth(){
        return "d";
    }

    public String dayOfWeekInMonth(){
        return "F";
    }

    public String dayOfWeekName(){
        return "E";
    }

    public String dayOfWeekNumber(){
        return "u";
    }

    public String amPMMarker(){
        return "a";
    }

    public String hourOfDay(){
        return "H";
    }

    public String hourOfDayAMPM(){
        return "h";
    }

    public String minuteInHour(){
        return "m";
    }

    public String secondInMinute(){
        return "s";
    }

    public String timezonePST(){
        return "z";
    }

    public String timezoneRFC822(){
        return "Z";
    }

    public String timezoneISO8601(){
        return "X";
    }
    
    /*
    H	Hour in day (0-23)	Number	0
    k	Hour in day (1-24)	Number	24
    K	Hour in am/pm (0-11)	Number	0
    h	Hour in am/pm (1-12)	Number	12
    S	Millisecond	Number	978
    */

}
