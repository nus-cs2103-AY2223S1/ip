import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.Deadline;



public class DeadlineTest {
    @Test
    public void testEmptyDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Deadline deadline = new Deadline("", false, deadlineDateTime);
        assertEquals("", deadline.getDescription());
    }

    @Test
    public void testChangeIsDone() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Deadline deadline = new Deadline("test", false, deadlineDateTime);

        deadline.changeIsDone(true);
        assertEquals(true, deadline.getIsDone());

        deadline.changeIsDone(false);
        assertEquals(false, deadline.getIsDone());
    }

    @Test
    public void testSuccessfulCanChangeIsDone() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Deadline deadline = new Deadline("test", false, deadlineDateTime);

        assertEquals(true, deadline.canChangeIsDone(true));

        assertEquals(false, deadline.canChangeIsDone(false));

        deadline.changeIsDone(true);

        assertEquals(false, deadline.canChangeIsDone(true));

        assertEquals(true, deadline.canChangeIsDone(false));
    }

    @Test
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 2 2022 06:54 AM", formatter);
        Deadline deadline = new Deadline("", false, deadlineDateTime);

        assertEquals("[D][ ]  (by: 2-Sep-2022 06:54 AM)", deadline.toString());

        deadline.changeIsDone(true);

        assertEquals("[D][X]  (by: 2-Sep-2022 06:54 AM)", deadline.toString());

        deadline.changeIsDone(false);

        assertEquals("[D][ ]  (by: 2-Sep-2022 06:54 AM)", deadline.toString());

        deadline.setDescription("Eat food");

        assertEquals("[D][ ] Eat food (by: 2-Sep-2022 06:54 AM)", deadline.toString());

        deadline.changeIsDone(true);

        assertEquals("[D][X] Eat food (by: 2-Sep-2022 06:54 AM)", deadline.toString());
    }

    @Test
    public void testIncorrectMonthFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sept 2 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sept 2 2022 06:54 AM' could not be parsed at index 3", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("September 2 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'September 2 2022 06:54 AM' could not be parsed at index 3", e.getMessage());
        }
    }

    @Test
    public void testIncorrectDayFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 32 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 32 2022 06:54 AM' could not be parsed: Invalid value for "
                            + "DayOfMonth (valid values 1 - 28/31): 32", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep -1 2022 06:54 AM", formatter);
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
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 22 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 22 06:54 AM' could not be parsed at index 7", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 00 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 00 06:54 AM' could not be parsed at index 7", e.getMessage());
        }
    }

    @Test
    public void testIncorrectTimeFormat() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 2022 06:61 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 06:61 AM' could not be parsed: Invalid value for "
                    + "MinuteOfHour (valid values 0 - 59): 61", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 2022 15:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 15:54 AM' could not be parsed: Invalid value for "
                    + "ClockHourOfAmPm (valid values 1 - 12): 15", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 2022 12:54", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 12:54' could not be parsed at index 17", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 2022 12:54 AMM", formatter);
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
            LocalDateTime deadlineDateTime = LocalDateTime.parse("15 Sep 2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15 Sep 2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("15-Sep-2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15-Sep-2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("15-09-2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15-09-2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("15/09/2022 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15/09/2022 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("15/09/22 06:54 AM", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text '15/09/22 06:54 AM' could not be parsed at index 0", e.getMessage());
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
            LocalDateTime deadlineDateTime = LocalDateTime.parse("Sep 15 2022 2359", formatter);
            fail();
        } catch (Exception e) {
            assertEquals("Text 'Sep 15 2022 2359' could not be parsed at index 14", e.getMessage());
        }
    }
}
