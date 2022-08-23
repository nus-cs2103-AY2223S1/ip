package duke.models.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    @Test
    public void testMarkAsDone() {
        Task task = new Event("TaskA", LocalDate.parse("2022-11-11"));
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void testMarkAsUndone() {
        Task task = new Event("TaskA", LocalDate.parse("2022-11-11"));
        task.markAsUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void testStringConversion() {
        Task task = new Event("TaskA", LocalDate.parse("2022-11-11"));
        assertEquals("[E] [ ] TaskA (at: Nov 11 2022)", task.toString());
    }
}
