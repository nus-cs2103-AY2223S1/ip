import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import ip.Storage;
import ip.TaskList;


public class StorageTest {
    private final Storage storage = new Storage("src/test/existingData.txt");

    @Test
    public void loadTest_existingFile_taskList() {
        try {
            TaskList taskList = storage.load();
            assertFalse(taskList.tasks.isEmpty());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void loadTest_emptyExistingFile_emptyList() {
        try {
            TaskList taskList = storage.load("src/test/emptyData.txt");
            assertTrue(taskList.tasks.isEmpty());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void loadTest_noExistingFile_emptyList() {
        try {
            TaskList taskList = storage.load("src/test/badFile.txt");
            storage.wipe();
            assertTrue(taskList.tasks.isEmpty());
        } catch (IOException e) {
            fail();
        }
    }
}
