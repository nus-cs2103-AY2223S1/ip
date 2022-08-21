package duke.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;

public class StorageTest {
    private static final String directoryPath = "data";
    private static final String filePath = "data/tasks";

    @Test
    public void load_loadTasks_noException() {
        Storage storage = new Storage(directoryPath, filePath);
        assertDoesNotThrow(() -> {
            storage.load();
        });
    }

    @Test
    public void save_saveTasks_noException() {
        Storage storage = new Storage(directoryPath, filePath);
        assertDoesNotThrow(() -> storage.save(new TaskList()));
    }
}
