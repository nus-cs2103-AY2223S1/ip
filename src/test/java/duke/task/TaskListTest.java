package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testGetCountStatement_notOne() {
        TaskList tasks = new TaskList();
        assertEquals("0 tasks", tasks.lengthString());
    }

    @Test
    public void testGetCountStatement_one() {
        Task testTask = new TodoTask("test", false);
        TaskList tasks = new TaskList();
        tasks.add(testTask);
        assertEquals("1 task", tasks.lengthString());
    }

    @Test
    public void testAdd() {
        Task testTask = new TodoTask("test", false);
        TaskList tasks = new TaskList();
        tasks.add(testTask);
        assertEquals("1. [T][ ] test\n", tasks.toString());
    }

    @Test
    public void testDelete_success() {
        TaskList tasks = new TaskList();
        Task expectedTask = new TodoTask("test", false);
        tasks.add(expectedTask);
        Task actualTask = tasks.remove(0);
        assertEquals("0 tasks", tasks.lengthString());
        assertEquals(expectedTask, actualTask);
    }
}
