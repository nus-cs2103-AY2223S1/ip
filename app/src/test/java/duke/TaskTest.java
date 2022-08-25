package duke;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void isDone_isDone_shouldBeDone() {
        Task task = new Todo("hello");
        task.markDone();
        assertTrue("Task should be done", task.isDone());
    }

    @Test
    public void isDone_isNotDone_shouldBeUndone() {
        Task task = new Todo("hello");
        assertFalse("Task should be undone", task.isDone());
    }

    @Test
    public void getStatusIcon_isDone_shouldBeX() {
        Task task = new Todo("hello");
        task.markDone();
        assertEquals("Task status icon should be an X", task.getStatusIcon(), Task.ICON_DONE);
    }

    @Test
    public void getStatusIcon_isNotDone_shouldBeBlank() {
        Task task = new Todo("hello");
        assertEquals("Task status icon should be blank", task.getStatusIcon(), Task.ICON_UNDONE);
    }
}
