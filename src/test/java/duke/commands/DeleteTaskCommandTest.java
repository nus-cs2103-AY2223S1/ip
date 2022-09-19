package duke.commands;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.TaskType;
import duke.utils.InputParser;
import duke.utils.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTaskCommandTest {

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
    public void deleteTaskAndUndoTest() {
        taskList.addTask(new Event("Sample event", LocalDate.parse("2022-02-02")));
        DeleteTaskCommand cmd = new DeleteTaskCommand(storage, taskList, "1");
        cmd.execute();
        assertEquals(taskList.getSize(), 0);
        cmd.undo();
        assertEquals(taskList.getSize(), 1);
    }

}
