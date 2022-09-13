package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DueCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.parser.Parser;

public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void testToDoCommand() {
        Command command = parser.parse("todo high read book");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void testDeadlineCommand() {
        Command command = parser.parse("deadline high lab /by 2019-12-10 16:30");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void testEventCommand() {
        Command command = parser.parse("event meeting /at 2019-12-10 16:30-20:30");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void testFindCommand() {
        Command command = parser.parse("find book lab");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void testMarkCommand() {
        Command command = parser.parse("mark 3");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testUnmarkCommand() {
        Command command = parser.parse("unmark 3");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void testDeleteCommand() {
        Command command = parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testListCommand() {
        Command command = parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testDueCommand() {
        Command command = parser.parse("due 2019-12-10");
        assertTrue(command instanceof DueCommand);
    }

    @Test
    public void testPriorityCommand() {
        Command command = parser.parse("priority 5 high");
        assertTrue(command instanceof PriorityCommand);
    }

    @Test
    public void testExitCommand() {
        Command command = parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testInvalidDateExceptionThrown() {
        try {
            parser.parse("due 2019");
        } catch (DukeException e) {
            assertEquals("Input a valid date in the format YYYY-MM-DD", e.getMessage());
        }
    }


    @Test
    public void testInvalidIndexExceptionThrown() {
        try {
            parser.parse("mark s");
        } catch (DukeException e) {
            assertEquals("Unfortunate, input a valid integer", e.getMessage());
        }
    }

    @Test
    public void testInvalidInputExceptionThrown() {
        try {
            parser.parse("hi");
        } catch (DukeException e) {
            assertEquals("This means nothing to me", e.getMessage());
        }
    }


    @Test
    public void testPriorityExceptionThrown() {
        try {
            parser.parse("priority 5 med");
        } catch (DukeException e) {
            assertEquals("Invalid priority status entered", e.getMessage());
        }
    }

    @Test
    public void testMissingDescriptionExceptionThrown() {
        try {
            parser.parse("priority");
        } catch (DukeException e) {
            assertEquals("Unfortunate, the description of a priority cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testIncompletePriorityExceptionThrown() {
        try {
            parser.parse("priority 5");
        } catch (DukeException e) {
            assertEquals("Unfortunate, the description of a priority is incomplete", e.getMessage());
        }
    }

}
