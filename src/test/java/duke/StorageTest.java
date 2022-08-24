package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void readFromFile_noInput_emptyTaskList() {
        Storage storage = new Storage("data.txt");
        TaskList taskList = new TaskList();
        try {
            assertEquals(storage.readFromFile(), taskList);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void writeToAndReadFromFile_twoTasks_taskListWithTwoTasks() {
        Storage storage = new Storage("data.txt");
        TaskList taskList = new TaskList();
        try {
            taskList.addTask("abc", "todo", false, false);
            taskList.addTask("def", "todo", false, false);
            storage.writeToFile(taskList);
            assertEquals(storage.readFromFile(), taskList);
        } catch (DukeException e) {
            fail();
        }
    }
}
