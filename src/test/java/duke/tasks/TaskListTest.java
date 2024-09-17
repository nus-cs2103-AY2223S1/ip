package duke.tasks;

import duke.data.Storage;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void deleteTask_emptyTaskList_dukeExceptionThrown() {
        boolean thrown = false;
        Storage testStorage = new Storage("./test");
        TaskList taskList = new TaskList(testStorage);
        try {
            taskList.deleteTask("1");
        } catch (DukeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void markTask_emptyTaskList_dukeExceptionThrown() {
        boolean thrown = false;
        Storage testStorage = new Storage("./test");
        TaskList taskList = new TaskList(testStorage);
        try {
            taskList.markTask("1", true);
        } catch (DukeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
