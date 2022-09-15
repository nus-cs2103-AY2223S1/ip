package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.ByeCommand;
import duke.commands.NumericCommand;


public class ParserTest {
    @Test
    public void parse_invalidMarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        try {
            assertEquals(parser.parseInput("mark"), new NumericCommand("mark", 0));
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter an argument after the command", e.getMessage());
        }
    }

    @Test
    public void parse_emptyMarkCommand_exceptionThrown() {
        Parser parser = new Parser();
        try {
            assertEquals(parser.parseInput(""), new ByeCommand());
            fail();
        } catch (DukeException e) {
            assertEquals("No suitable command with that name!\nPlease type help for a list of available commands", e.getMessage());
        }
    }
}
