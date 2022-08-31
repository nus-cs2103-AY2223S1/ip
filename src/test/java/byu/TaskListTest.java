package byu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.InvalidIndexException;
import task.Task;
import task.ToDo;

public class TaskListTest {

    @Test
    public void add_success() {
        TaskList list = new TaskList();
        Task t = new ToDo("sleep");
        list.addTask(t);
        assertEquals(1, list.getNumOfTasks());
    }

    @Test
    public void mark_validIndex_success() throws InvalidIndexException {
        TaskList list = new TaskList();
        Task t = new ToDo("sleep");
        list.addTask(t);
        list.mark(1);
        assertTrue(t.isDone());
    }

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            Task t = new ToDo("sleep");
            list.addTask(t);
            list.mark(2);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals("The task does not exist!", e.getMessage());
        }
    }
}
