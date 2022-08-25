package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void toCsv_taskList_taskListCsvReturned() throws IOException {
        String filePath = "./src/test/sample-read-data";
        File dataFile = new File(filePath);
        TaskList testList = new TaskList(dataFile);
        String expected = Files.readString(Path.of(filePath));
        expected = expected.replace("\n", "").replace("\r", "");
        String actual = testList.toCsv();
        actual = actual.replace("\n", "").replace("\r", "");
        assertEquals(expected, actual);
    }
}
