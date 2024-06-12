package doemon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getSaveString_stringDueAt_unformattedSaveString() {
        Task event = new Event("This is a test event", "test date");
        String expected = "E | 0 | This is a test event | test date";
        assertEquals(expected, event.getSaveString());
    }

    @Test
    public void getSaveString_localDateDueAt_unformattedSaveString() {
        Task event = new Event("This is a test event", "2023-05-16 2359");
        String expected = "E | 0 | This is a test event | 2023-05-16 2359";
        assertEquals(expected, event.getSaveString());
    }

    @Test
    public void toString_stringDueAt_unformattedString() {
        Task event = new Event("This is a test event", "test date");
        String expected = "[E][ ] This is a test event (at: test date)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toString_localDateDueAt_reformattedString() {
        Task event = new Event("This is a test event", "2023-05-16 2359");
        String expected = "[E][ ] This is a test event (at: May 16 2023 11:59PM)";
        assertEquals(expected, event.toString());
    }
}
