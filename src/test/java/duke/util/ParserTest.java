package duke.util;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

class ParserTest {
    @Test
    public void parse_acceptableCommand_success() throws DukeException, NumberFormatException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
        assertInstanceOf(TodoCommand.class, Parser.parse("todo read book"));
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline read book /by 28/08/2022 1800"));
        assertInstanceOf(EventCommand.class, Parser.parse("event read book /at 28/08/2022 1800"));
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"));
        assertInstanceOf(InvalidCommand.class, Parser.parse("hi"));
    }
}
