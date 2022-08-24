package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.numberOfTasks());
        assertTrue(taskList.getTaskList() instanceof ArrayList);
    }

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Read Book"));
        assertEquals("[T][ ] Read Book",taskList.getTask(0).toString());

    }
}
