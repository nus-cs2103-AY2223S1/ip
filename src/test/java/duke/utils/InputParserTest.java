package duke.utils;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.tasks.TaskList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTest {

    private static InputParser inputParser;
    private static Storage storage;
    private static TaskList taskList;
    private static Deque<Command> commandHistory;

    @BeforeAll
    public static void setup() {
        inputParser = new InputParser();
        storage = new Storage(new File("testdata.txt"));
        taskList = new TaskList(storage.loadFromFile());
        commandHistory = new LinkedList<>();
    }

    @AfterAll
    public static void cleanup() {
        new File("testdata.txt").delete();
    }

    @Test
    public void parseTest() {
        String bye = "bye";
        assertEquals(inputParser.parse(bye, taskList, storage, commandHistory).getClass(), ExitCommand.class);
    }

}
