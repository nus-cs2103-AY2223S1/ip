package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    @DisplayName("Test assert DukeException in parseDeadline (empty description)")
    public void parseDeadline_emptyDescription_throwsDukeException() {
        Exception dukeException = assertThrows(duke.DukeException.class,
                () -> new Parser("deadline /by 2022-09-01 12:00".split(" ", 2)).parseDeadline());
        assertEquals("Oops! You forgot to indicate the description for your deadline", dukeException.getMessage());
    }
    @Test
    @DisplayName("Test assert DukeException in parseDeadline (missing /by)")
    public void parseDeadline_missingBy_throwsDukeException() {
        Exception dukeException = assertThrows(duke.DukeException.class,
                () -> new Parser("deadline submit resume 2022-09-01 12:00".split(" ", 2)).parseDeadline());
        assertEquals("Oops! You forgot to use /by to separate the description and deadline",
                dukeException.getMessage());
    }
    @Test
    @DisplayName("Test assert DukeException in parseDeadline (empty deadline)")
    public void parseDeadline_emptyDeadline_throwsDukeException() {
        Exception dukeException = assertThrows(duke.DukeException.class,
                () -> new Parser("deadline submit resume /by ".split(" ", 2)).parseDeadline());
        assertEquals("Oops! You forgot to indicate the deadline", dukeException.getMessage());
    }
    @Test
    @DisplayName("Test assert DateTimeParseException in parseDeadline (invalid datetime format)")
    public void parseDeadline_invalidDateTimeFormat_throwsDateTimeException() {
        DateTimeParseException dateTimeException = assertThrows(DateTimeParseException.class,
                () -> new Parser("deadline submit resume /by 1 Sept 2022 12:00 "
                        .split(" ", 2)).parseDeadline());
        assertEquals("Text '1 Sept 2022 12:00' could not be parsed at index 0", dateTimeException.getMessage());
    }

}