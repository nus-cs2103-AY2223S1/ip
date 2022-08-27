package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import date.EventDateTime;
import exception.DukeException;

class EventTest {

    @Test
    @DisplayName("Test for toString() method of unmarked event")
    void testToString_for_unmarked_event() throws DukeException {
        EventDateTime date = EventDateTime.parseDate("2022-01-01 00:00:00 12:00:00");
        Event test = new Event("Testing!", date);
        String expected = "[E][ ] Testing! (at: Jan 1 2022 00:00:00 - 12:00:00)";
        assertEquals(expected, test.toString());
    }

    @Test
    @DisplayName("Test for toString() method of marked event")
    void testToString_for_marked_event() throws DukeException {
        EventDateTime date = EventDateTime.parseDate("2022-01-01 00:00:00 12:00:00");
        Event test = new Event("Testing!", date);
        test.setIsMarked(true);
        String expected = "[E][X] Testing! (at: Jan 1 2022 00:00:00 - 12:00:00)";
        assertEquals(expected, test.toString());
    }

    @Test
    @DisplayName("Test for encode() method of event")
    void encode() throws DukeException {
        EventDateTime date = EventDateTime.parseDate("2022-01-01 00:00:00 12:00:00");
        Event test = new Event("Testing!", date);
        String expected = "E,0,Testing!,2022-01-01|00:00:00|12:00:00\n";
        assertEquals(expected, test.encode());
    }
}