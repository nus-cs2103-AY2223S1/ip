package duke.storage;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
