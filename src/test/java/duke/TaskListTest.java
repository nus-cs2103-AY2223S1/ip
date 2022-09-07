package duke;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static final String FILE_PATH = "./src/test/test-data/sample-read-data";
    private File testDataFile = new File(FILE_PATH);
    private TaskList testList = new TaskList(testDataFile);

    @Test
    public void toCsv_taskList_taskListCsvReturned() throws IOException {
        String expected = Files.readString(Path.of(FILE_PATH));
        expected = expected.replace("\n", "").replace("\r", "");
        String actual = testList.toCsv();
        actual = actual.replace("\n", "").replace("\r", "");
        assertEquals(expected, actual);
    }

    @Test
    public void isEmpty_nonEmptyTaskList_false() {
        assertEquals(testDataFile.length() == 0, testList.isEmpty());
    }
}
