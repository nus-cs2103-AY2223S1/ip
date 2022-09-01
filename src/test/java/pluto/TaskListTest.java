package pluto;

import org.junit.jupiter.api.Test;
import pluto.task.Task;
import pluto.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void deleteTask_validIndex_success() throws PlutoException {
        Task t1 = new Todo("test task1");
        Task t2 = new Todo("test task2");
        Task t3 = new Todo("test task3");
        TaskList tasks = new TaskList();
        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);
        assertEquals(t3, tasks.deleteTask(2));
    }
    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        try {
            Task t = new Todo("test task");
            TaskList tasks = new TaskList();
            tasks.addTask(t);
            tasks.addTask(t);
            tasks.addTask(t);
            tasks.deleteTask(5);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("\tOOPS!!! Valid index required.", e.getMessage());
        }
    }
    @Test
    public void markTask_validIndex_success() throws PlutoException {
        Task t1 = new Todo("test task1");
        Task t2 = new Todo("test task2");
        Task t3 = new Todo("test task3");
        TaskList tasks = new TaskList();
        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);
        tasks.markTask(1, true);
        assertEquals(t2.getStatusIcon(), "X");
    }
}
