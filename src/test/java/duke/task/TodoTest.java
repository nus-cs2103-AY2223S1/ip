package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testMarkAsDone() {
        Task task = new Todo("Task");
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void testMarkNotDone() {
        Task task = new Todo("Task");
        task.markNotDone();
        assertFalse(task.isDone());
    }

    @Test
    public void testToString() {
        Task task = new Todo("Task");
        assertEquals("[T][ ] Task", task.toString());
    }
}
