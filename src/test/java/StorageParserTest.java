import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import stubs.StorageStub;
import utility.StorageParser;
import task.Task;
import storage.Storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
public class StorageParserTest {
    private StorageStub s = new StorageStub(Paths.get("tasksStub.txt"));

    @Test
    public void test_file_line_to_task() {
        try {
            assert(StorageParser.fileLineToTask("[T][ ] task1") instanceof  Task);
        } catch (DukeException d) {
            fail();
        }
    }
}
