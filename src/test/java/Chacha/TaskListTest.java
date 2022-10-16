package chacha;

import org.junit.jupiter.api.Test;

import chacha.tasks.Task;
import chacha.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        Task testTask = new Todo("testTask");
        tasks.add(testTask);
        Task task = tasks.get(0);
        assertEquals("testTask", task.getDescription());
    }

    @Test
    public void getTaskTest() {
        TaskList tasks = new TaskList();
        Task testTask = new Todo("testTask");
        tasks.add(testTask);
        Task task = tasks.get(0);
        assertEquals("testTask", task.getDescription());
    }
}
