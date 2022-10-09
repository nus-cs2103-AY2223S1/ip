package duke;

import duke.storage.Storage;
import duke.tasks.Task;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    void test1() throws DukeException {
        Storage dummyStorage = new Storage("./src/test/dummyinputs/dummytest1.txt");
        List<Task> tasks = dummyStorage.load();
    }

    @Test
    void test2() throws DukeException {
        try {
            Storage dummyStorage = new Storage("./src/test/dummyinputs/wrongfile.txt");
            List<Task> tasks = dummyStorage.load();
        } catch (DukeException e ) {
            assertEquals(e.getMessage(), "File Not Found!");
        }
    }
}
