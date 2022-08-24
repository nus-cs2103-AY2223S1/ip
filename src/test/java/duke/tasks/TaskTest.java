package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIconTest() {
        Task todoTask = new ToDoTask("read book", true);
        assertEquals("[X]", todoTask.getStatusIcon());
    }

    @Test
    public void markUndoneTest() {
        Task todoTask = new ToDoTask("read book", true);
        todoTask.markUndone();
        assertEquals("[ ]", todoTask.getStatusIcon());

    }
}
