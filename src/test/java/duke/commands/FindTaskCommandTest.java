package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.utils.InputParser;
import duke.utils.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTaskCommandTest {

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
    public void findTaskTest() {
        taskList.addTask(new Todo("blablabla"));
        taskList.addTask(new Todo("albalbalb"));
        taskList.addTask(new Todo("blankblank"));

        FindTaskCommand cmd = new FindTaskCommand(taskList, "bla");
        String res = cmd.execute();
        String exp = "Here are the matching tasks in your list:\n" +
                "1.[T] [ ] blablabla\n" +
                "2.[T] [ ] blankblank";
        assertEquals(res, exp);
    }

}
