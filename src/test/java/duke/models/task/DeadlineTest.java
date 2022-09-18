package duke.models.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testMarkAsDone() {
        Task task = new Deadline("TaskA", LocalDate.parse("2022-11-11"));
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void testMarkAsUndone() {
        Task task = new Deadline("TaskA", LocalDate.parse("2022-11-11"));
        task.markAsUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void testStringConversion() {
        Task task = new Deadline("TaskA", LocalDate.parse("2022-11-11"));
        assertEquals("[D] [ ] TaskA (by: Nov 11 2022)", task.toString());
    }
}
