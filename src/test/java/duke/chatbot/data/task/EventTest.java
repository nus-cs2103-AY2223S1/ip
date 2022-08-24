package duke.chatbot.data.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_noBoolean_prefixAdded() {
        assertEquals("[E][ ] Lecture (at: Jan 01 2000, 0000hrs)",
                new Event("Lecture", LocalDateTime.of(2000, 1, 1, 0, 0)).toString());
    }

    @Test
    public void toString_hasBoolean_boxTicked() {
        assertEquals("[E][X] Lecture (at: Jan 01 2000, 0000hrs)",
                new Event("Lecture", LocalDateTime.of(2000, 1, 1, 0, 0), true).toString());
    }

    @Test
    public void toString_emptyDescription_prefixAndDateOnly() {
        assertEquals("[E][ ]  (at: Jan 01 2000, 0000hrs)",
                new Event("", LocalDateTime.of(2000, 1, 1, 0, 0)).toString());
    }

    @Test
    public void encode_noBoolean_stringWithZero() {
        assertEquals("E,,,0,,,Lecture,,,2000-01-01 0000",
                new Event("Lecture", LocalDateTime.of(2000, 1, 1, 0, 0)).encode());
    }

    @Test
    public void encode_haveBoolean_stringWithOne() {
        assertEquals("E,,,1,,,Lecture,,,2000-01-01 0000",
                new Event("Lecture", LocalDateTime.of(2000, 1, 1, 0, 0), true).encode());
    }

    @Test
    public void encode_emptyDescription_manyCommas() {
        assertEquals("E,,,0,,,,,,2000-01-01 0000",
                new Event("", LocalDateTime.of(2000, 1, 1, 0, 0)).encode());
    }

    @Test
    public void hasMatchingDate_matchingDate_returnTrue() {
        assertEquals(true,
                new Event("Lecture",
                        LocalDateTime.of(2000, 1, 1, 0, 0)).hasMatchingDate("2000-01-01"));
    }

    @Test
    public void hasMatchingDate_noMatchingDayOfTheMonth_returnFalse() {
        assertEquals(false,
                new Event("Lecture",
                        LocalDateTime.of(2000, 1, 1, 0, 0)).hasMatchingDate("2000-01-02"));
    }

    @Test
    public void hasMatchingDate_noMatchingMonth_returnFalse() {
        assertEquals(false,
                new Event("Lecture",
                        LocalDateTime.of(2000, 1, 1, 0, 0)).hasMatchingDate("2000-02-01"));
    }

    @Test
    public void hasMatchingDate_noMatchingYear_returnFalse() {
        assertEquals(false,
                new Event("Lecture",
                        LocalDateTime.of(2000, 1, 1, 0, 0)).hasMatchingDate("2001-01-01"));
    }

    @Test
    public void hasMatchingDate_wrongDateStringFormat_exceptionThrown() {
        assertEquals(false,
                new Event("Lecture",
                        LocalDateTime.of(2000, 1, 1, 0, 0)).hasMatchingDate("abc123"));
    }
}
