import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import stubs.StorageStub;
import utility.StorageParser;
import task.Task;
import java.util.List;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;
public class StorageParserTest {
    private StorageStub storageStub = new StorageStub(Paths.get("tasksStub.txt"));

    @Test
    public void test_file_line_to_task() {
        try {
            List<String> lines = storageStub.getAllLines();
            for (int i  = 0; i < lines.size(); i ++) {
                assert (StorageParser.fileLineToTask(lines.get(i)) instanceof Task);
            }
        } catch (DukeException d) {
            fail();
        }
    }
}
