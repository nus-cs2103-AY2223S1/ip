package duke.utils;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.tasks.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTest {

    private static InputParser inputParser;
    private static Storage storage;
    private static Ui ui;
    private static TaskList taskList;
    private static Deque<Command> commandHistory;

    @BeforeAll
    public static void setup() {
        inputParser = new InputParser();
        ui = new Ui();
        storage = new Storage(new File("savedata.txt"));
        taskList = new TaskList(storage.loadFromFile());
        commandHistory = new LinkedList<>();
    }

    @Test
    public void parseTest() {
        String bye = "bye";
        assertEquals(inputParser.parse(bye, taskList, storage, ui, commandHistory).getClass(), ExitCommand.class);
    }

}
