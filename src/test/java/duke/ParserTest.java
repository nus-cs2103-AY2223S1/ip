package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parseInput_whiteSpace_exceptionThrown() throws DukeException {
        try {
            assertTrue(Parser.parse("") instanceof Command);
            fail();
        } catch (DukeException de) {
            assertEquals("Command cannot be empty", de.getMessage());
        }
    }

    @Test
    public void parseInput_bye_returnByeCommand() throws DukeException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }
}
