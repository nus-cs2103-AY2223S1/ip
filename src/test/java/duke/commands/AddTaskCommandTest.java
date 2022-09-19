package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.utils.InputParser;
import duke.utils.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskCommandTest {

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
    public void addTaskAndUndoTest() {
        String str = "Sample event /at 2022-02-02";
        AddTaskCommand cmd = new AddTaskCommand(storage, taskList, TaskType.EVENT, str);
        cmd.execute();
        assertEquals(taskList.getSize(), 1);
        cmd.undo();
        assertEquals(taskList.getSize(), 0);
    }

}
