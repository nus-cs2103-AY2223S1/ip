package sally.parser;

import sally.command.AddDeadlineCommand;
import sally.command.AddEventCommand;
import sally.command.AddTodoCommand;
import sally.command.ByeCommand;
import sally.command.Command;
import sally.command.DeleteCommand;
import sally.command.ListCommand;
import sally.command.MarkCommand;
import sally.command.UnmarkCommand;
import sally.exception.SallyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parserTest() throws SallyException {
        assertTrue(Parser.parseCommand("bye") instanceof ByeCommand);
        assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        assertTrue(Parser.parseCommand("mark 2") instanceof MarkCommand);
        assertTrue(Parser.parseCommand("delete 2") instanceof DeleteCommand);

        assertTrue(Parser.parseCommand("todo testing todo") instanceof AddTodoCommand);
        assertTrue(Parser.parseCommand("deadline testing deadline /by string") instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommand("deadline testing deadline /by 09-12-2022") instanceof AddDeadlineCommand);
        assertTrue(Parser.parseCommand("event testing event /at place") instanceof AddEventCommand);
    }
}
