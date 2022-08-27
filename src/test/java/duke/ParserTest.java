package duke;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_deleteCommand() {
        Command expected = new DeleteCommand(1);
        String inputCommand = "delete 2";
        try {
            Command actual = Parser.parse(inputCommand);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_deleteCommandWithoutIndex_exceptionThrown() {
        String inputCommand = "delete";
        try {
            Parser.parse(inputCommand);
            fail("Parser did not throw correct error when parsing invalid delete command.");
        } catch (DukeException e) {
            String actualError = e.getMessage();
            String expectedError = "Task ID supplied is not a number.";
            assertEquals(expectedError, actualError);
        }
    }

    @Test
    public void parse_markCommand() {
        Command expected = new MarkCommand(3);
        String inputCommand = "mark 4";
        try {
            Command actual = Parser.parse(inputCommand);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

}
