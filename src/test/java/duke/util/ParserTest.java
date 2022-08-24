package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parseCommand_commandStrings_returnCorrectCommandTypes() {
        try {
            assertEquals(true, Parser.parseCommand("help") instanceof HelpCommand);
            assertEquals(true, Parser.parseCommand("bye") instanceof ByeCommand);
            assertEquals(true, Parser.parseCommand("list") instanceof ListCommand);
            assertEquals(true, Parser.parseCommand("") instanceof EmptyCommand);
            assertEquals(true, Parser.parseCommand("gibberish") instanceof UnknownCommand);
            assertEquals(true, Parser.parseCommand("delete 0") instanceof DeleteCommand);
            assertEquals(true, Parser.parseCommand("mark 0") instanceof MarkCommand);
            assertEquals(true, Parser.parseCommand("unmark 0") instanceof UnmarkCommand);
            assertEquals(true, Parser.parseCommand("todo task") instanceof AddCommand);
            assertEquals(true, Parser.parseCommand("deadline a /by 2022-08-08") instanceof AddCommand);
            assertEquals(true, Parser.parseCommand("event a /at 2022-08-08") instanceof AddCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseDate_dateStrings_returnLocalDate() {
        try {
            assertEquals(true, Parser.parseDate("2022-08-08") instanceof LocalDate);
            assertEquals(true, Parser.parseDate("2022-12-20") instanceof LocalDate);
        } catch (DukeException e) {
            fail();
        }
    }
}
