package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.ListCommand;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private static final String TODO_DESCRIPTION = "todo Finish Assignment";
    private static final String EVENT_DESCRIPTION = "event Maroon 5 Concert /at 20/02/2022 2000";
    private static final String DEADLINE_DESCRIPTION = "deadline CS2102 Group Project /by 12/03/2022 2359";

    @Test
    public void parse_unknownCommand_throwsException() {
        try {
            Parser.parse("run");
        } catch (DukeException err) {
            assertEquals("I'm sorry, but I don't know what that means", err.getMessage());
        }
    }

    @Test
    public void parseCommand_createEventCommand_success() throws DukeException {
        assertTrue(Parser.parse(EVENT_DESCRIPTION) instanceof AddEventCommand);
    }

    @Test
    public void parseCommand_createToDoCommand_success() throws DukeException {
        assertTrue(Parser.parse(TODO_DESCRIPTION) instanceof AddTodoCommand);
    }

    @Test
    public void parseCommand_createDeadlineCommand_success() throws DukeException {
        assertTrue(Parser.parse(DEADLINE_DESCRIPTION) instanceof AddDeadlineCommand);
    }

    @Test
    public void parseCommand_CreateListCommand_success() throws DukeException{
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parseCommand_exitDuke_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
    }
}
