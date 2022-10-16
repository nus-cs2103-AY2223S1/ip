package chacha;

import org.junit.jupiter.api.Test;

import chacha.tasks.Task;
import chacha.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void add_task_success() {
        TaskList tasks = new TaskList();
        Task testTask = new Todo("testTask");
        tasks.add(testTask);
        Task task = tasks.get(0);
        assertEquals("testTask", task.getDescription());
    }

    @Test
    public void get_task_success() {
        TaskList tasks = new TaskList();
        Task testTask = new Todo("testTask");
        tasks.add(testTask);
        Task task = tasks.get(0);
        assertEquals("testTask", task.getDescription());
    }

    @Test
    public void mark_task_success() throws ChachaException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("test"));
        taskList.get(0).markAsDone();
        assertEquals("[T][X] test", taskList.get(0).toString());
    }

    @Test
    public void unmark_task_success() throws ChachaException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("test"));
        taskList.get(0).markAsDone();
        taskList.get(0).unmarkAsDone();
        assertEquals("[T][X] test", taskList.get(0).toString());
    }
    
}
