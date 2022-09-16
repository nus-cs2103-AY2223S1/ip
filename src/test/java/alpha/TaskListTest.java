package alpha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import alpha.task.Task;
import alpha.task.Todo;



public class TaskListTest {
    private List<Task> tasks = new ArrayList<>();
    private TaskList taskList = new TaskList(tasks);

    /**
     * Tests whether the taskList correctly modifies the status of the tasks or not.
     */
    @Test
    public void modifiyTaskStatus_taskNumber_alphaException() {
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
