package kohaku;

import static org.junit.jupiter.api.Assertions.fail;

import kohaku.datastruct.TaskList;
import kohaku.daveexceptions.DaveException;
import kohaku.daveexceptions.DaveNoTasksException;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    /**
     * Tests that trying to remove a task from an empty task list throws an exception.
     */
    @Test
    public void remove_fromEmptyTaskList_throwsDaveNoTaskException() {
        try {
            TaskList tasks = new TaskList();
            tasks.remove(2);
            fail();
        } catch (DaveNoTasksException ignored) {
        }
    }

    /**
     * Tests that trying to get a task that is not in the task list throws an exception.
     */
    @Test
    public void get_invalidTaskFromTaskList_throwsDaveException() {
        try {
            TaskList tasks = new TaskList();
            tasks.get(3);
            fail();
        } catch (DaveException ignored) {
        }
    }
}
