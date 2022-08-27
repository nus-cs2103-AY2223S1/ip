package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void loadTest() throws DukeException, IOException {
        Storage storage = new Storage("data/tasks.txt");
        TaskList tasks = new TaskList(storage.load());
        int expectedSize = 3;
        int actualSize = tasks.getTaskArrayList().size();
        assertEquals(expectedSize, actualSize);
    }
}
