package byu;

import exceptions.InvalidIndex;
import org.junit.jupiter.api.Test;
import task.ToDos;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {

    @Test
    public void add_success() {
        ToDoList list = new ToDoList();
        Task t = new ToDos("sleep");
        list.addTask(t);
        assertEquals(1, list.getNumOfTasks());
    }

    @Test
    public void mark_validIndex_success() throws InvalidIndex {
        ToDoList list = new ToDoList();
        Task t = new ToDos("sleep");
        list.addTask(t);
        list.mark(1);
        assertEquals(true, t.isDone());
    }

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        try {
            ToDoList list = new ToDoList();
            Task t = new ToDos("sleep");
            list.addTask(t);
            list.mark(2);
            fail();
        } catch (InvalidIndex e) {
            assertEquals("The task does not exist!", e.getMessage());
        }
    }
}
