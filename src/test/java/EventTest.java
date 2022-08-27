import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.Event;


public class EventTest {
    public void testEmptyDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime eventDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Event event = new Event("", false, eventDateTime);
        assertEquals("", event.getDescription());
    }

    @Test
    public void testChangeIsDone() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime eventDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Event event = new Event("test", false, eventDateTime);

        event.changeIsDone(true);
        assertEquals(true, event.getIsDone());

        event.changeIsDone(false);
        assertEquals(false, event.getIsDone());
    }

    @Test
    public void testSuccessfulCanChangeIsDone() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime eventDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Event event = new Event("test", false, eventDateTime);

        assertEquals(true, event.canChangeIsDone(true));

        assertEquals(false, event.canChangeIsDone(false));

        event.changeIsDone(true);

        assertEquals(false, event.canChangeIsDone(true));

        assertEquals(true, event.canChangeIsDone(false));
    }

    @Test
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime eventDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Event event = new Event("", false, eventDateTime);

        assertEquals("[E][ ]  (at: 2-Sep-2022 06:54 AM)", event.toString());

        event.changeIsDone(true);

        assertEquals("[E][X]  (at: 2-Sep-2022 06:54 AM)", event.toString());

        event.changeIsDone(false);

        assertEquals("[E][ ]  (at: 2-Sep-2022 06:54 AM)", event.toString());

        event.setDescription("Eat food");

        assertEquals("[E][ ] Eat food (at: 2-Sep-2022 06:54 AM)", event.toString());

        event.changeIsDone(true);

        assertEquals("[E][X] Eat food (at: 2-Sep-2022 06:54 AM)", event.toString());
    }

    @Test
    public void testIncorrectMonthFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sept 2 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sept 2 2022 06:54 AM' could not be parsed at index 3", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("September 2 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'September 2 2022 06:54 AM' could not be parsed at index 3", e.getMessage());
        }
    }

    @Test
    public void testIncorrectDayFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 32 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 32 2022 06:54 AM' could not be parsed: Invalid value for "
                            + "DayOfMonth (valid values 1 - 28/31): 32", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep -1 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep -1 2022 06:54 AM' could not be parsed: Invalid value for "
                            + "DayOfMonth (valid values 1 - 28/31): -1", e.getMessage());
        }
    }

    @Test
    public void testIncorrectYearFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 22 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 22 06:54 AM' could not be parsed at index 7", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 00 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 00 06:54 AM' could not be parsed at index 7", e.getMessage());
        }
    }

    @Test
    public void testIncorrectTimeFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 2022 06:61 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 06:61 AM' could not be parsed: Invalid value for "
                    + "MinuteOfHour (valid values 0 - 59): 61", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 2022 15:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 15:54 AM' could not be parsed: Invalid value for "
                    + "ClockHourOfAmPm (valid values 1 - 12): 15", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 2022 12:54", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 12:54' could not be parsed at index 17", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 2022 12:54 AMM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 12:54 AMM' could not be parsed, "
                    + "unparsed text found at index 20", e.getMessage());
        }
    }

    @Test
    public void testIncorrectFormattedDateTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("15 Sep 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15 Sep 2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("15-Sep-2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15-Sep-2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("15-09-2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15-09-2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("15/09/2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15/09/2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("15/09/22 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15/09/22 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime eventDateTime = LocalDateTime.parse("Sep 15 2022 2359", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 2359' could not be parsed at index 14", e.getMessage());
        }
    }
}
