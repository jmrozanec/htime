package com.cronutils.htime.jdk12;

import com.cronutils.htime.HDateTimeFormat;
import com.cronutils.htime.HDateTimeFormatBuilder;
import org.joda.time.DateTime;
import org.junit.Before;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class JDK12HDateTimeFormatTest {
    private HDateTimeFormat<SimpleDateFormat> formatter;

    @Before
    public void setUp() {
        formatter = HDateTimeFormatBuilder.getInstance().forJDK12().getFormatter();
    }


    public void testForPattern() throws Exception {
        assertForPattern("MMMM d, YYYY", "June 9, 2011");
        assertForPattern("MMM d, YYYY", "Jun 9, 2011");
        assertForPattern("MMM dd", "Jun 09");
        assertForPattern("EEEE, MMMM d, YYYY", "Thursday, June 9, 2011");
        assertForPattern("EEE MMM d", "Thu Jun 9");
        assertForPattern("MM/dd/YY", "06/09/11");
        assertForPattern("hh:mm:ss a", "01:00:00 AM");
        assertForPattern("HH:mm", "20:52");
        assertForPattern("HH:mm:ss", "01:00:00");
        assertForPattern("HH:mm Z", "01:00 America/Los_Angeles");
    }

    private void assertForPattern(String dateTimePattern, String readablePattern) {
        DateTime now = DateTime.now();
        System.out.println(String.format("%s :: %s", dateTimePattern, readablePattern));
        assertEquals(
                new SimpleDateFormat(dateTimePattern).format(now.toDate()),
                formatter.forPattern(readablePattern).format(DateTime.now().toDate())
        );
    }
}