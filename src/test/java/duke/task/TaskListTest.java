package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void taskListTest() {
        try {
            TaskList taskList = new TaskList();
            assertEquals(0, taskList.size());
            assertTrue(taskList.getTasks() instanceof ArrayList);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addTaskTest() {
        try {
            TaskList taskList = new TaskList();
            Task task = new ToDo("Sleep");
            taskList.loadTask(task);
            assertEquals(1, taskList.size());
        } catch (Exception e) {
            fail();
        }
    }

}
