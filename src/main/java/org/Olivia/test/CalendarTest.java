package org.Olivia.test;

import org.Olivia.calendar.Calendar;
import org.Olivia.calendar.CalendarEntry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalendarTest {

    @Test
    public void getEntry() {
        try {
            Calendar c = new Calendar();
            CalendarEntry to_add = new CalendarEntry("whatever");
            c.addEntry(to_add);
            assertEquals(c.getEntry(1), to_add);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}