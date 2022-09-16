package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void size_taskListOfSizeThree_success() {
        TaskList tasks = new TaskList();
        Task task = new Todo("todo task");
        for (int i = 0; i < 3; i++) {
            tasks.addTask(task);
        }
        assertEquals(3, tasks.size());
    }

    @Test
    public void addTask_todoTask_success() {
        TaskList tasks = new TaskList();
        Task task = new Todo("todo task");
        tasks.addTask(task);
        assertEquals(1, tasks.size());
    }
}
