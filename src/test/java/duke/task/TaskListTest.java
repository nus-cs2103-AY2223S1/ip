package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * The TaskListTest class tests the methods in the TaskList class.
 *
 * @author Leong Jia Hao Daniel
 */
public class TaskListTest {

    /**
     * Tests the tasks list size and implementation.
     */
    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.numberOfTasks());
        assertTrue(taskList.getTaskList() instanceof ArrayList);
    }

    /**
     * Tests the adding of tasks.
     */
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Read Book"));
        assertEquals("[T][ ] Read Book", taskList.getTask(0).toString());

    }
}
