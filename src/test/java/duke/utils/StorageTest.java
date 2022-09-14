package duke.utils;

import duke.DukeException;
import duke.TaskListStub;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Storage class.
 */
public class StorageTest {
    private final String PATH_FILE = "src/test/data/duke.txt";
    private final String PATH_DIRECTORY = "src/test/data";

    /**
     * Test Case 1 of load() method.
     * Tests for a singular task in save file.
     */
    @Test
    public void load_loadingOneTaskData_dataLoadedCorrectly() throws DukeException, IOException {
        Storage storage = new Storage(PATH_FILE, PATH_DIRECTORY);
        TaskListStub taskList = new TaskListStub(storage.load());
        String taskString = taskList.get(0).toString();

        assertEquals(taskString, "[D][ ] test (by: Feb 2 2022)");
    }

    /**
     * Test Case 2 of load() method.
     * Tests for multiple tasks in save file.
     */
    @Test
    public void load_loadingMultipleTaskData_dataLoadedCorrectly() throws DukeException, IOException {
        Storage storage = new Storage(PATH_FILE, PATH_DIRECTORY);
        TaskListStub taskList = new TaskListStub(storage.load());
        String deadlineTaskString = taskList.get(0).toString();
        String toDoTaskString = taskList.get(1).toString();
        String eventTaskString = taskList.get(2).toString();

        String taskString = deadlineTaskString + " " + toDoTaskString + " " + eventTaskString;

        assertEquals(taskString, "[D][ ] test (by: Feb 2 2022) [T][X] test 2 [E][ ] test 3 (at: Feb 2 2022)");
    }
}
