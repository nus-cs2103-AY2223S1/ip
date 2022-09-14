package rattus.chatbot.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_noBoolean_prefixAdded() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "[D][ ] Complete unit testing (by: Jan 01 2000, 0000hrs)";
        String actual = new Deadline(description, dateTime).toString();
        assertEquals(actual, expected);
    }

    @Test
    public void toString_hasBoolean_boxTicked() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "[D][X] Complete unit testing (by: Jan 01 2000, 0000hrs)";
        String actual = new Deadline(description, dateTime, true).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toString_emptyDescription_prefixAndDateOnly() {
        String description = "";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "[D][ ]  (by: Jan 01 2000, 0000hrs)";
        String actual = new Deadline(description, dateTime).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_noBoolean_stringWithZero() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "D`0`Complete unit testing`2000-01-01 0000";
        String actual = new Deadline(description, dateTime).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_haveBoolean_stringWithOne() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "D`1`Complete unit testing`2000-01-01 0000";
        String actual = new Deadline(description, dateTime, true).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_emptyDescription_manyCommas() {
        String description = "";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "D`0``2000-01-01 0000";
        String actual = new Deadline(description, dateTime).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void hasMatchingDate_matchingDate_returnTrue() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2000, 1, 1);
        assertTrue(new Deadline(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void hasMatchingDate_noMatchingDayOfTheMonth_returnFalse() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2000, 1, 2);
        assertFalse(new Deadline(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void hasMatchingDate_noMatchingMonth_returnFalse() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2000, 2, 1);
        assertFalse(new Deadline(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void hasMatchingDate_noMatchingYear_returnFalse() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2001, 1, 1);
        assertFalse(new Deadline(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void equals_deadlineAllSame_equal() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime);
        Deadline second = new Deadline(description, dateTime);
        assertEquals(first, second);
    }

    @Test
    public void equals_deadlineDifferentDescription_notEqual() {
        String description1 = "Complete unit testing";
        String description2 = "Complete assertions";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description1, dateTime);
        Deadline second = new Deadline(description2, dateTime);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_deadlineDifferentCompletion_notEqual() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime);
        Deadline second = new Deadline(description, dateTime, true);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_deadlineDifferentDate_notEqual() {
        String description = "Complete unit testing";
        LocalDateTime dateTime1 = LocalDateTime.of(2000, 1, 2, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime1);
        Deadline second = new Deadline(description, dateTime2);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_deadlineDifferentTime_notEqual() {
        String description = "Complete unit testing";
        LocalDateTime dateTime1 = LocalDateTime.of(2000, 1, 1, 1, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime1);
        Deadline second = new Deadline(description, dateTime2);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_deadlineOneNull_notEqual() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime);
        Deadline second = null;
        assertNotEquals(first, second);
    }

    @Test
    public void equals_deadlineAndToDo_notEqual() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime);
        ToDo second = new ToDo(description);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_deadlineAndEvent_notEqual() {
        String description = "Complete unit testing";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Deadline first = new Deadline(description, dateTime);
        Event second = new Event(description, dateTime);
        assertNotEquals(first, second);
    }
}
