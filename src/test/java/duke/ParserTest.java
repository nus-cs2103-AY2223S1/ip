package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.data.exception.DukeException;
import duke.parser.Parser;

public class ParserTest {

    @Test
    public void checkDate_wrongFormat_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.isDateValid("20 dec 2022"));
    }

    @Test
    public void parse_deadline_success() {
        assertDoesNotThrow(() -> Parser.parse("deadline submit project /by 20/12/2022"));
    }

    @Test
    public void parse_todo_success() {
        assertDoesNotThrow(() -> Parser.parse("todo read book"));
    }
}
