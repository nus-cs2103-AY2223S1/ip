import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;
import stubs.StorageStub;
import task.Task;
import utility.StorageParser;

public class StorageParserTest {
    private StorageStub storageStub = new StorageStub(Paths.get("tasksStub.txt"));

    @Test
    public void fileLineToTask_convertsStringToTask_taskReturned() {
        try {
            List<String> lines = storageStub.getAllLines();
            for (int i = 0; i < lines.size(); i++) {
                assert (StorageParser.fileLineToTask(lines.get(i)) instanceof Task);
            }
        } catch (DukeException d) {
            fail();
        }
    }
}
