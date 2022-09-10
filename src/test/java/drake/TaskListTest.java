package drake;

import drake.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {
    @Test
    public void isValidTaskNumber_smokeTest() {
        TaskList taskList = new TaskList(Arrays.asList(null, null));
        assertFalse(taskList.isValidTaskNumber(3));
    }

    @Test
    public void getSizeToString_smokeTest() {
        TaskList taskList = new TaskList(Arrays.asList(null, null));
        assertEquals("You now have 2 tasks in the list", taskList.getSizeToString());
    }

    @Test
    public void addTask_smokeTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        assertNull(taskList.addTask(null));
    }
}
