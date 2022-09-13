package duke.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;

public class StorageTest {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/tasks";

    @Test
    public void load_loadTasks_noException() {
        Storage storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        assertDoesNotThrow(() -> {
            storage.load();
        });
    }

    @Test
    public void save_saveTasks_noException() {
        Storage storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        assertDoesNotThrow(() -> storage.save(new TaskList()));
    }
}
