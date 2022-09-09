package doemon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getSaveString_stringDueBy_unformattedSaveString() {
        Task deadline = new Deadline("This is a test deadline", "test date");
        String expected = "D | 0 | This is a test deadline | test date";
        assertEquals(expected, deadline.getSaveString());
    }

    @Test
    public void getSaveString_localDateDueBy_unformattedSaveString() {
        Task deadline = new Deadline("This is a test deadline", "2023-05-16 2359");
        String expected = "D | 0 | This is a test deadline | 2023-05-16 2359";
        assertEquals(expected, deadline.getSaveString());
    }

    @Test
    public void toString_stringDueBy_unformattedString() {
        Task deadline = new Deadline("This is a test deadline", "test date");
        String expected = "[D][ ] This is a test deadline (by: test date)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_localDateDueBy_reformattedString() {
        Task deadline = new Deadline("This is a test deadline", "2023-05-16 2359");
        String expected = "[D][ ] This is a test deadline (by: May 16 2023 11:59PM)";
        assertEquals(expected, deadline.toString());
    }
}
