package duke;

import duke.duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest_success() throws DukeException {
        Storage storage = new Storage("data/test.txt");
        TaskList taskList = new TaskList(storage.loadTaskList());
        assertEquals("1.[D][ ][High] feed cat (by: Dec 12 2020)\n2.[T][ ][Medium] feed dog\n",taskList.printTaskList());
    }
}