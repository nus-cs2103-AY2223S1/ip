package duke.utils;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.UnrecognisedCommand;
import duke.tasks.TaskList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void parseByeTest() {
        String in = "bye";
        Command cmd = inputParser.parse(in, taskList, storage, commandHistory);
        assertTrue(inputParser.parse(in, taskList, storage, commandHistory) instanceof ExitCommand);
    }

    @Test
    public void parseTodoTest() {
        String in = "todo new todo 1";
        Command cmd = inputParser.parse(in, taskList, storage, commandHistory);
        assertTrue(inputParser.parse(in, taskList, storage, commandHistory) instanceof AddTaskCommand);
    }

    @Test
    public void parseUnrecognisedTest() {
        String in = "12blabla$12";
        Command cmd = inputParser.parse(in, taskList, storage, commandHistory);
        assertTrue(inputParser.parse(in, taskList, storage, commandHistory) instanceof UnrecognisedCommand);
    }

}
