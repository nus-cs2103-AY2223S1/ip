package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {
    @Test
    void parse_exitCommand_success() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_listCommand_success() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    void parse_markCommand_success() throws DukeException {
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
    }

    @Test
    void parse_unmarkCommand_success() throws DukeException {
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
    }

    @Test
    void parse_addCommand_success() throws DukeException {
        assertTrue(Parser.parse("todo test") instanceof AddCommand);
    }

    @Test
    void parse_deleteCommand_success() throws DukeException {
        assertTrue(Parser.parse("delete 1 2") instanceof DeleteCommand);
    }

    @Test
    void parse_taskOnDateCommand_success() throws DukeException {
        assertTrue(Parser.parse("on 2022-05-05") instanceof TaskOnDateCommand);
    }

    @Test
    void parse_findCommand_success() throws DukeException {
        assertTrue(Parser.parse("find keyword") instanceof FindCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> Parser.parse("idk"));
        assertEquals("I don't know this command!", exception.getMessage());
    }

    @Test
    public void parseDate_validDate_success() {
        try {
            assertEquals(Parser.parseDate("2022-05-05"), LocalDate.parse("2022-05-05"));
        } catch (DukeException exception) {
            fail("Valid dates should not throw DukeExceptions.");
        }
    }

    @Test
    public void parseDate_invalidDate_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> Parser.parseDate("date"));
        assertEquals("date is not a date.", exception.getMessage());
    }

}
