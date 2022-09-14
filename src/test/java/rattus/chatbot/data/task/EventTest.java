package rattus.chatbot.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_noBoolean_prefixAdded() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "[E][ ] Lecture (at: Jan 01 2000, 0000hrs)";
        String actual = new Event(description, dateTime).toString();
        assertEquals(actual, expected);
    }

    @Test
    public void toString_hasBoolean_boxTicked() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "[E][X] Lecture (at: Jan 01 2000, 0000hrs)";
        String actual = new Event(description, dateTime, true).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toString_emptyDescription_prefixAndDateOnly() {
        String description = "";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "[E][ ]  (at: Jan 01 2000, 0000hrs)";
        String actual = new Event(description, dateTime).toString();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_noBoolean_stringWithZero() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "E`0`Lecture`2000-01-01 0000";
        String actual = new Event(description, dateTime).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_haveBoolean_stringWithOne() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "E`1`Lecture`2000-01-01 0000";
        String actual = new Event(description, dateTime, true).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void encode_emptyDescription_manyCommas() {
        String description = "";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        String expected = "E`0``2000-01-01 0000";
        String actual = new Event(description, dateTime).encode();
        assertEquals(expected, actual);
    }

    @Test
    public void hasMatchingDate_matchingDate_returnTrue() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2000, 1, 1);
        assertTrue(new Event(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void hasMatchingDate_noMatchingDayOfTheMonth_returnFalse() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2000, 1, 2);
        assertFalse(new Event(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void hasMatchingDate_noMatchingMonth_returnFalse() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2000, 2, 1);
        assertFalse(new Event(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void hasMatchingDate_noMatchingYear_returnFalse() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        LocalDate date = LocalDate.of(2001, 1, 1);
        assertFalse(new Event(description, dateTime).hasMatchingDate(date));
    }

    @Test
    public void equals_eventAllSame_equal() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime);
        Event second = new Event(description, dateTime);
        assertEquals(first, second);
    }

    @Test
    public void equals_eventDifferentDescription_notEqual() {
        String description1 = "Lecture";
        String description2 = "Career fair";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description1, dateTime);
        Event second = new Event(description2, dateTime);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_eventDifferentCompletion_notEqual() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime);
        Event second = new Event(description, dateTime, true);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_eventDifferentDate_notEqual() {
        String description = "Lecture";
        LocalDateTime dateTime1 = LocalDateTime.of(2000, 1, 2, 0, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime1);
        Event second = new Event(description, dateTime2);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_eventDifferentTime_notEqual() {
        String description = "Lecture";
        LocalDateTime dateTime1 = LocalDateTime.of(2000, 1, 1, 1, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime1);
        Event second = new Event(description, dateTime2);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_eventOneNull_notEqual() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime);
        Event second = null;
        assertNotEquals(first, second);
    }

    @Test
    public void equals_eventAndToDo_notEqual() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime);
        ToDo second = new ToDo(description);
        assertNotEquals(first, second);
    }

    @Test
    public void equals_eventAndDeadline_notEqual() {
        String description = "Lecture";
        LocalDateTime dateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Event first = new Event(description, dateTime);
        Deadline second = new Deadline(description, dateTime);
        assertNotEquals(first, second);
    }
}
