package duke.utils;

import duke.commands.ExitCommand;
import duke.tasks.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParserTest {

    private static InputParser inputParser;
    private static Storage storage;
    private static Ui ui;
    private static TaskList taskList;

    @BeforeAll
    public static void setup() {
        inputParser = new InputParser();
        ui = new Ui();
        storage = new Storage(new File("savedata.txt"));
        taskList = new TaskList(storage.loadFromFile());
    }

    @Test
    public void parseTest() {
        String bye = "bye";
        assertEquals(inputParser.parse(bye, taskList, storage, ui).getClass(), ExitCommand.class);
    }

}
