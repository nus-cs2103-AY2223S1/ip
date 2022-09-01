package alpha;

import alpha.task.Task;
import alpha.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    List<Task> tasks = new ArrayList<>();
    TaskList taskList = new TaskList(tasks);

    @Test
    public void modifiyTaskStatus_taskNumber_AlphaException() {
        taskList.addToTaskList(new Todo("read", "T"));
        taskList.addToTaskList(new Todo("dance", "T"));
        try {
            taskList.modifyTaskStatus(3, true);
        } catch (AlphaException e) {
            assertEquals(new AlphaException("Invalid input: This task number doesn't exist!"), e);
        }
        try {
            taskList.modifyTaskStatus(-1, false);
        } catch (AlphaException e) {
            assertEquals(new AlphaException("Invalid input: This task number doesn't exist!"), e);
        }
    }
}
