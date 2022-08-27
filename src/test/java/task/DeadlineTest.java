package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import date.DeadlineDateTime;
import exception.DukeException;

class DeadlineTest {

    @Test
    @DisplayName("Test for toString() method of unmarked deadline")
    void testToString_for_unmarked_deadline() throws DukeException {
        DeadlineDateTime date = DeadlineDateTime.parseDate("2022-01-01 00:00:00");
        Deadline test = new Deadline("Testing!", date);
        String expected = "[D][ ] Testing! (by: Jan 1 2022 00:00:00)";
        assertEquals(expected, test.toString());
    }

    @Test
    @DisplayName("Test for toString() method of marked deadline")
    void testToString_for_marked_deadline() throws DukeException {
        DeadlineDateTime date = DeadlineDateTime.parseDate("2022-01-01 00:00:00");
        Deadline test = new Deadline("Testing!", date);
        test.setIsMarked(true);
        String expected = "[D][X] Testing! (by: Jan 1 2022 00:00:00)";
        assertEquals(expected, test.toString());
    }

    @Test
    @DisplayName("Test for encode() method of deadline")
    void encode() throws DukeException {
        DeadlineDateTime date = DeadlineDateTime.parseDate("2022-01-01 00:00:00");
        Deadline test = new Deadline("Testing!", date);
        String expected = "D,0,Testing!,2022-01-01|00:00:00\n";
        assertEquals(expected, test.encode());
    }
}