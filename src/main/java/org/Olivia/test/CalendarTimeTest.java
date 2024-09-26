package org.Olivia.test;


import org.testng.annotations.Test;
import static org.junit.Assert.*;
import org.Olivia.calendar.CalendarTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarTimeTest {

    @Test
    public void testParseInput() {
        try {
            assertEquals(CalendarTime.parseInput("09/08/2021"), new CalendarTime(LocalDate.of(2021,8,9)));
            assertEquals(CalendarTime.parseInput("19:00"), new CalendarTime(LocalTime.of(19,0)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToString() {
        try {
            assertEquals(CalendarTime.parseInput("09/08/2021").toString(), "09/08/2021");
            assertEquals(CalendarTime.parseInput("19:00").toString(), "19:00");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}