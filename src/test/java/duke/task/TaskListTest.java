package duke.task;

import duke.exceptions.NoSuchTaskException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private static TaskList getTaskListWithSingleItem() {
        return new TaskList(new ArrayList<>() {{
            add(new Todo("Test", false));
        }});
    }

    @Test
    public void store_insertsSingleItem() {
        TaskList taskList = getTaskListWithSingleItem();
        int prevNumTasks = taskList.getNumTasks();

        Task task = new Task("Test task");
        taskList.store(task);

        assertEquals(taskList.getNumTasks(), prevNumTasks + 1);
    }

    @Test
    public void store_insertsCorrectItem() throws NoSuchTaskException {
        TaskList taskList = getTaskListWithSingleItem();
        int prevNumTasks = taskList.getNumTasks();

        Task task = new Task("Test task");
        taskList.store(task);

        assertEquals(taskList.get(prevNumTasks), task);
    }

    @Test
    public void delete_validIndex_exceptionNotThrown() {
        TaskList taskList = getTaskListWithSingleItem();

        assertDoesNotThrow(() -> taskList.delete(0));
    }

    @Test
    public void delete_invalidIndex_exceptionThrown() {
        TaskList taskList = getTaskListWithSingleItem();

        assertThrows(NoSuchTaskException.class, () -> taskList.delete(1));
    }

    @Test
    public void delete_validIndex_removesSingleItem() throws NoSuchTaskException {
        TaskList taskList = getTaskListWithSingleItem();
        int prevNumTasks = taskList.getNumTasks();

        taskList.delete(0);

        assertEquals(taskList.getNumTasks(), prevNumTasks - 1);
    }

    @Test
    public void delete_removesCorrectItem() throws NoSuchTaskException {
        TaskList taskList = getTaskListWithSingleItem();
        int prevNumTasks = taskList.getNumTasks();

        Task task = new Task("Another task");
        taskList.store(task);

        taskList.delete(1);

        assertEquals(taskList.getAll().stream().filter(e -> e == task).toArray().length, 0);
    }
}
