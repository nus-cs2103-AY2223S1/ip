package duke;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parse_byeCommand_returnExitCommand() throws DukeException {
        Parser parser = new Parser();
        Command actual = parser.parse("bye");
        assertTrue(actual instanceof ExitCommand);
    }

    @Test
    public void parse_markCommand_returnMarkCommand() throws DukeException {
        Parser parser = new Parser();
        Command actual = parser.parse("mark 2");
        assertTrue(actual instanceof MarkCommand);
    }

    @Test
    public void parse_markCommandWithIndex2_return1() throws DukeException {
        Parser parser = new Parser();
        Command actual = parser.parse("mark 2");
        MarkCommand markCmd = (MarkCommand) actual;
    }

    @Test
    public void parse_invalidDeleteCommand_exceptionThrown() throws DukeException {
        Parser parser = new Parser();
        try {
            Command actual = parser.parse("delete");
            assertTrue(actual instanceof DeleteCommand);
        } catch (DukeException e) {
            assertEquals(e.toString(),
                    "duke.exception.DukeException: ☹ OOPS!!! The index of a task cannot be empty.");
        }
    }

    @Test
    public void parse_deleteCommand_returnDeleteCommand() throws DukeException {
        Parser parser = new Parser();
        try {
            Command actual = parser.parse("delete 3");
            DeleteCommand deleteCmd = (DeleteCommand) actual;
            assertEquals(2, deleteCmd.getNum());
            assertTrue(actual instanceof DeleteCommand);
        } catch (DukeException e) {
            assertEquals(e.getMessage(),
                    "☹ OOPS!!! The index of a task cannot be empty.");
        }
    }
}
