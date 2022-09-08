package doemon.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void saveStringForStringDueByTest() {
        Task event = new Event("This is a test event", "test date");
        String expected = "E | 0 | This is a test event | test date";
        assertEquals(expected, event.saveString());
    }

    @Test
    public void saveStringForLocalDateDueByTest() {
        Task event = new Event("This is a test event", "2023-05-16 2359");
        String expected = "E | 0 | This is a test event | 2023-05-16 2359";
        assertEquals(expected, event.saveString());
    }

    @Test
    public void toStringForStringDueByTest() {
        Task event = new Event("This is a test event", "test date");
        String expected = "[E][ ] This is a test event (at: test date)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void toStringForLocalDateDueByTest() {
        Task event = new Event("This is a test event", "2023-05-16 2359");
        String expected = "[E][ ] This is a test event (at: May 16 2023 11:59PM)";
        assertEquals(expected, event.toString());
    }
}
