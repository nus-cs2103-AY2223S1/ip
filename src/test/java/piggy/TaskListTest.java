package piggy;

import org.junit.jupiter.api.Test;
import piggy.task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Task("Test task");
        taskList.add(task);
        assertEquals(taskList.get(0), task);
    }
}
