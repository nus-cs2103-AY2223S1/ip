package storage;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class storageTest {
    @Test
    public void test1() {
        String path = "data/testStorage.txt";
        Storage storage = new Storage(path);
        ArrayList<Task> task = storage.load();
        assertEquals(3, storage.getSize());
    }

    @Test
    public void test2() {
        Storage storage = new Storage("data/fakeFile.txt");
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        assertEquals(0,tasks.taskListSize() );
    }
}
