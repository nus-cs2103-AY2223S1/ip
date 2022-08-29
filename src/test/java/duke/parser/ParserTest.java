package duke.parser;

import duke.command.Command;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_validTodoCommand_success() {
        try {
            Parser parser = new Parser("todo assignment 0");
            parser.parse();
            assertEquals(Command.TODO, parser.getCommand());
            assertEquals("assignment 0", parser.getArgs()[0]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_emptyCommand_exceptionThrown() {
        try {
            Parser parser = new Parser("");
            parser.parse();
            fail();
        } catch (DukeException e) {
            assertEquals("Please enter a command!", e.getMessage());
        }
    }

    @Test
    public void parse_invalidEventArguments_exceptionThrown() {
        try {
            Parser parser = new Parser("event event100 \\at");
            parser.parse();
            fail();
        } catch (DukeException e) {
            assertEquals("Input format: event (event name) \\at (event date/time)", e.getMessage());
        }
    }
}
