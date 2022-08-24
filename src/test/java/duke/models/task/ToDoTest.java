package duke.models.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {
    @Test
    public void testMarkAsDone() {
        Task task = new ToDo("TaskA");
        task.markAsDone();t
        assertTrue(task.isDone);
    }

    @Test
    public void testMarkAsUndone() {
        Task task = new ToDo("TaskA");
        task.markAsUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void testStringConversion() {
        Task task = new ToDo("TaskA");
        assertEquals("[T] [ ] TaskA", task.toString());
    }
}
