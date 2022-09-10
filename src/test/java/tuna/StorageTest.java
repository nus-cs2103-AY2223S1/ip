package tuna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests to test the Storage class.
 */
public class StorageTest {

    /**
     * Tests if the loadFileContents function works as intended, which should load the tasks from the data file into
     * the tasks list.
     *
     * @throws TunaException exception thrown when data file has incorrect formatting.
     */
    @Test
    public void loadFileContents_dataFile_tasksListUpdated() throws TunaException {
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        storage.loadFileContents("./data", "./data/tuna.txt", tasks);
        assertEquals(6, tasks.getTotalTasks());
    }
}
