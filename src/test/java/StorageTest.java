import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.main.Storage;
import duke.task.Task;



/**
 * Tests the storage class
 */
public class StorageTest {
    private final String File_Path = "src/test/data/Duke.txt";

    /**
     * Test Case of load() method.
     * Tests for loading a task in saved file.
     */
    @Test
    public void load_loadingOneTask_dataLoaded() throws IOException {
        Storage storage = new Storage(File_Path);
        TaskListStub taskList = new TaskListStub(storage.loadTasks());
        Task task = taskList.getTask(0);

        assertEquals("[T][] help", task.toString());
    }
}
