import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.main.Storage;
import duke.task.Task;
import duke.task.Todo;


/**
 * Tests the storage class
 */
public class StorageTest {
    private final String filePath = "src/test/data/duke.main.Duke.txt";

    /**
     * Test Case of load() method.
     * Tests for loading a task in saved file.
     */
    @Test
    public void load_loadingOneTask_dataLoaded() throws IOException {
        Storage storage = new Storage(filePath);
        TaskListStub taskList = new TaskListStub(storage.loadTasks());
        taskList.addTasks(new Todo("todoTest"));
        Task task = taskList.getTask(0);

        assertEquals("[T][ ] todoTest", task.toString());
    }
}
