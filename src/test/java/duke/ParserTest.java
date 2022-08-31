package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.ByeCommand;
import duke.commands.NumericCommand;


public class ParserTest {
    @Test
    public void parse_invalidMarkCommand_exceptionThrown() {
        Parser parser = new Parser(true);
        try {
            assertEquals(parser.parseInput("mark"), new NumericCommand("mark", 0));
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter an argument (number) after mark!", e.getMessage());
        }
    }

    @Test
    public void parse_emptyMarkCommand_exceptionThrown() {
        Parser parser = new Parser(true);
        try {
            assertEquals(parser.parseInput(""), new ByeCommand());
            fail();
        } catch (DukeException e) {
            assertEquals("No suitable name for that task", e.getMessage());
        }
    }
}
