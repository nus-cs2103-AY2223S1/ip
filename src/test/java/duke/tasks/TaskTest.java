package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void getStatusIconTest() {
        Task todoTask = new ToDoTask("read book", true);
        assertEquals("[X]", todoTask.getStatusIcon());
    }

    @Test
    public void markUndoneTest() {
        Task todoTask = new ToDoTask("read book", true);
        todoTask.setUndone();
        assertEquals("[ ]", todoTask.getStatusIcon());

    }
}
