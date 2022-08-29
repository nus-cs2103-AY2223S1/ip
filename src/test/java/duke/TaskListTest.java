package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void add_task_taskAdded() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("homework"));
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void remove_task_taskRemoved() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("homework"));
        tasks.remove(1);
        assertEquals(0, tasks.getSize());
    }
}
