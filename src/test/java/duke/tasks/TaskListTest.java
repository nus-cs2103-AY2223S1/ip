package duke.tasks;

import duke.utils.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private static Storage storage;
    private static TaskList taskList;

    @BeforeAll
    public static void setup() {
        storage = new Storage(new File("testdata.txt"));
        taskList = new TaskList(storage.loadFromFile());
    }

    @AfterAll
    public static void cleanup() {
        new File("testdata.txt").delete();
    }

    @Test
    public void findTest() {
        taskList.addTask(new Todo("blablabla"));
        taskList.addTask(new Todo("albalbalb"));
        taskList.addTask(new Todo("blankblank"));

        List<Task> res = taskList.find("bla");
        assertEquals(res.size(), 2);
    }

}
