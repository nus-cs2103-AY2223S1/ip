package duke;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;

public class ParserTest {
    @Test
    public void parse_deleteCommand() {
        List<Integer> indices = new ArrayList<>();
        indices.add(4);
        indices.add(3);
        indices.add(2);
        indices.add(1);
        Command expected = new DeleteCommand(indices);
        String inputCommand = "delete 2, 3,4,        5";
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
