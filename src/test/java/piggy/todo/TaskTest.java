package piggy.todo;

import org.junit.jupiter.api.Test;
import piggy.task.Task;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    @Test
    public void isDoneTest() {
        Task task = new Task("Hello world");
        assertFalse(task.isDone());
        task.markDone();
        assertTrue(task.isDone());
    }

    @Test
    public void getStatusIconTest() {
        Task task = new Task("Hello world");
        assertEquals(task.getStatusIcon(), " ");
        task.markDone();
        assertEquals(task.getStatusIcon(), "X");
    }
}
