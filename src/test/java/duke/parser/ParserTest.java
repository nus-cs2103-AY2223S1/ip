package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.exception.DukeException;

/**
 * Tests for the Parser class.
 *
 * @author Elgin
 */
public class ParserTest {
    @Test
    public void parse_toDoCommand_success() {
        assertTrue(Parser.parse("todo hello world") instanceof AddCommand);
    }

    @Test
    public void parse_deadlineCommand_success() {
        assertTrue(Parser.parse("deadline sleep /by 2020-12-12") instanceof AddCommand);
    }

    @Test
    public void parse_eventCommand_success() {
        assertTrue(Parser.parse("event eat /at 2020-01-01") instanceof AddCommand);
    }

    @Test
    public void parse_rejectsAddingEmptyTasks_throwsDukeException() {
        try {
            Parser.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a task cannot be empty!", e.getMessage());
        }

        try {
            Parser.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a task cannot be empty!", e.getMessage());
        }

        try {
            Parser.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a task cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void parse_rejectsInvalidCommand_throwsDukeException() {
        try {
            Parser.parse("SomeInvalidCommand");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, I don't understand what that means :-(", e.getMessage());
        }
    }
}
