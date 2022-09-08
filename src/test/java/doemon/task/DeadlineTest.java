package doemon.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void saveStringForStringDueByTest() {
        Task deadline = new Deadline("This is a test deadline", "test date");
        String expected = "D | 0 | This is a test deadline | test date";
        assertEquals(expected, deadline.saveString());
    }

    @Test
    public void saveStringForLocalDateDueByTest() {
        Task deadline = new Deadline("This is a test deadline", "2023-05-16 2359");
        String expected = "D | 0 | This is a test deadline | 2023-05-16 2359";
        assertEquals(expected, deadline.saveString());
    }

    @Test
    public void toStringForStringDueByTest() {
        Task deadline = new Deadline("This is a test deadline", "test date");
        String expected = "[D][ ] This is a test deadline (by: test date)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toStringForLocalDateDueByTest() {
        Task deadline = new Deadline("This is a test deadline", "2023-05-16 2359");
        String expected = "[D][ ] This is a test deadline (by: May 16 2023 11:59PM)";
        assertEquals(expected, deadline.toString());
    }
}
