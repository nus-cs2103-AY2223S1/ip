package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.utils.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MarkTaskCommandTest {

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
    public void markTaskAndUndoTest() {
        Task testTask = new Todo("blablabla");
        taskList.addTask(testTask);

        MarkTaskCommand cmd = new MarkTaskCommand(storage, taskList, "1");
        cmd.execute();
        assertTrue(testTask.isMarked());
        cmd.undo();
        assertFalse(testTask.isMarked());
    }

}
