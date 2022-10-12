package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.ListCommand;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private static final String TODO_DESCRIPTION = "todo Finish Assignment";
    private static final String EVENT_DESCRIPTION = "event Maroon 5 Concert /at 20/02/2022 2000";
    private static final String DEADLINE_DESCRIPTION = "deadline CS2102 Group Project /by 12/03/2022 2359";

    private static Storage storage = null;
    private static TaskList tasks = null;

    @BeforeAll
    public static void createFile() {
        storage = new Storage(Paths.get("dukeList_Test.txt"));
        tasks = new TaskList(new ArrayList<>(), storage);
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        try {
            Parser.parse("run", tasks);
        } catch (DukeException err) {
            assertEquals("I'm sorry, but I don't know what that means", err.getMessage());
        }
    }

    @Test
    public void parseCommand_createEventCommand_success() throws DukeException {
        assertTrue(Parser.parse(EVENT_DESCRIPTION, tasks) instanceof AddEventCommand);
    }

    @Test
    public void parseCommand_createToDoCommand_success() throws DukeException {
        assertTrue(Parser.parse(TODO_DESCRIPTION, tasks) instanceof AddTodoCommand);
    }

    @Test
    public void parseCommand_createDeadlineCommand_success() throws DukeException {
        assertTrue(Parser.parse(DEADLINE_DESCRIPTION, tasks) instanceof AddDeadlineCommand);
    }

    @Test
    public void parseCommand_CreateListCommand_success() throws DukeException{
        assertTrue(Parser.parse("list", tasks) instanceof ListCommand);
    }

    @Test
    public void parseCommand_exitDuke_success() throws DukeException {
        assertTrue(Parser.parse("bye", tasks) instanceof ByeCommand);
    }
}
