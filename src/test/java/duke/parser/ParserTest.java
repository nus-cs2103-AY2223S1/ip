package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.command.AddCommand;

/**
 * Tests for the Parser class.
 *
 * @author Elgin
 */
public class ParserTest {
    /**
     * Parser.parse returns AddCommand instance on passing in valid input (todo).
     *
     * @result Parser.parse should return an instance of AddCommand.
     */
    @Test
    public void parse_toDoCommand_success() {
        assertTrue(Parser.parse("todo hello world") instanceof AddCommand);
    }

    /**
     * Parser.parse returns AddCommand instance on passing in valid input (deadline).
     *
     * @result Parser.parse should return an instance of AddCommand.
     */
    @Test
    public void parse_deadlineCommand_success() {
        assertTrue(Parser.parse("deadline sleep /by 2020-12-12") instanceof AddCommand);
    }

    /**
     * Parser.parse returns AddCommand instance on passing in valid input (event).
     *
     * @result Parser.parse should return an instance of AddCommand.
     */
    @Test
    public void parse_eventCommand_success() {
        assertTrue(Parser.parse("event eat /at 2020-01-01") instanceof AddCommand);
    }

    /**
     * Parser.parse should throw DukeException on attempt to create an empty task.
     *
     * @result Throws DukeException for trying to create an empty todo, event or deadline task.
     */
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

    /**
     * Parser.parse throws DukeException on unrecognized command.
     *
     * @result Parser.parse throws DukeException on unrecognized command (e.g. SomeInvalidCommand).
     */
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
