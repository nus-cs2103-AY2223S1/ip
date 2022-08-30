package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest_success() throws DukeException {
        Storage storage = new Storage("data/test.txt");
        TaskList taskList = new TaskList(storage.loadTaskList());
        assertEquals("1.[D][ ] feed cat  (by: Dec 12 2020)\n2.[T][ ] feed dog\n",taskList.printTaskList());
    }
}