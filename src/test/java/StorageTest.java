import duke.main.Storage;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private final String FILE_PATH = "src/test/data/Duke.txt";

    /**
     * Test Case of load() method.
     * Tests for loading a task in saved file.
     */
    @Test
    public void load_loadingOneTask_dataLoaded() throws IOException {
        Storage storage = new Storage(FILE_PATH);
        TaskListStub taskList = new TaskListStub(storage.loadTasks());
        Task task = taskList.getTask(0);

        assertEquals("[T][] help", task.toString());
    }
}
