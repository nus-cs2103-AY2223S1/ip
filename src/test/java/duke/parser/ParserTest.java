package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;

public class ParserTest {

    @Test
    public void parse_byeCommand_success() {
        assertCommandType(ByeCommand.class, parseSuccessfully("bye"));
    }

    @Test
    public void parse_listCommand_success() {
        assertCommandType(ListCommand.class, parseSuccessfully("list"));
    }

    @Test
    public void parse_unknownCommand_success() {
        assertCommandType(UnknownCommand.class, parseSuccessfully("unknown /arg1 hello /arg2 world"));
    }

    @Test
    public void parse_markCommand_success() {
        assertCommandType(MarkCommand.class, parseSuccessfully("mark 1"));
        assertCommandType(MarkCommand.class, parseSuccessfully("mark 2"));
        assertCommandType(MarkCommand.class, parseSuccessfully("mark 20"));
    }

    @Test
    public void parse_markCommandNoParam_exceptionThrown() {
        assertEquals("You must pass an index value.", failToParse("mark").getMessage());
    }

    @Test
    public void parse_markCommandNaN_exceptionThrown() {
        assertEquals("You must pass an integer value. abc is not an integer.", failToParse("mark abc").getMessage());
    }

    @Test
    public void parse_markCommandNonPositive_exceptionThrown() {
        assertEquals("You must pass a positive integer value. -1 is a non-positive integer value.",
                failToParse("mark -1").getMessage());
    }

    private Command parseSuccessfully(String command) {
        try {
            return Parser.parse(command);
        } catch (DukeException e) {
            fail();
        }
        return null;
    }

    private DukeException failToParse(String command) {
        try {
            Parser.parse(command);
            fail();
        } catch (DukeException e) {
            return e;
        }
        return null;
    }

    // Adapted from https://stackoverflow.com/a/4294919.
    private void assertCommandType(Class<? extends Command> expected, Command actual) {
        assertTrue(expected.isInstance(actual));
    }
}
