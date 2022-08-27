package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Event e = new Event("concert", LocalDate.parse("2021-01-22"));
        assertEquals("[E][ ] concert (at: Jan 22 2021)", e.toString());
    }

    @Test
    void taskMemo() {
        Event e = new Event("concert", LocalDate.parse("2021-01-22"));
        assertEquals("E | 0 | concert | 2021-01-22", e.taskMemo());
    }
}