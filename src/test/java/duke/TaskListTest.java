package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * TaskListTest class to test TaskList object methods.
 */
public class TaskListTest {

    /**
     * Tests for adding a Task object to TaskList.
     */
    @Test
    public void addTask_normalTask_taskAdded() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("homework"));
        assertEquals(1, tasks.getSize());
    }

    /**
     * Tests for removing a Task object from TaskList.
     */
    @Test
    public void removeTask_normalTask_taskRemoved() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("homework"));
        tasks.remove(1);
        assertEquals(0, tasks.getSize());
    }
}
