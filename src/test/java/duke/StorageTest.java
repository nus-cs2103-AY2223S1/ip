package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void writeToAndReadFromFile_twoTasks_sucess() {
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

    @Test
    public void writeToAndReadFromFile_twoDeadlineTasks_success() {
        Storage storage = new Storage("data.txt");
        TaskList taskList = new TaskList();
        try {
            taskList.addTask("abc /by 2022-12-12", "deadline", false, false);
            taskList.addTask("def /by 2022-12-12", "deadline", false, false);
            storage.writeToFile(taskList);
            assertEquals(storage.readFromFile(), taskList);
        } catch (DukeException e) {
            fail();
        }
    }
}
