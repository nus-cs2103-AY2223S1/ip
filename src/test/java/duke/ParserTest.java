package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.data.exception.DukeException;
import duke.parser.Parser;


public class ParserTest {

    @Test
    public void parse_deadline_success() {
        assertDoesNotThrow(() -> Parser.parse("deadline submit project /by 20/12/2022"));
    }

    @Test
    public void parse_todo_success() {
        assertDoesNotThrow(() -> Parser.parse("todo read book"));
    }

    @Test
    public void parse_event_success() {
        assertDoesNotThrow(() -> Parser.parse("event swimming /at 20/12/2022"));
    }

    @Test
    public void parse_wrongDeadlineFormat_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("deadline blah"));
    }

    @Test
    public void parse_wrongEventFormat_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("event /at 20/12/2022"));
    }

    @Test
    public void parse_list_success() {
        assertDoesNotThrow(() -> Parser.parse("list"));
    }

    @Test
    public void parse_listWithInvalidArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("list abc"));
    }

    @Test
    public void parse_delete_success() {
        assertDoesNotThrow(() -> Parser.parse("delete 1"));
    }

    @Test
    public void parse_deleteWithInvalidArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("delete blah"));
    }

    @Test
    public void parse_deleteWithNoArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void parse_mark_success() {
        assertDoesNotThrow(() -> Parser.parse("mark 1"));
    }

    @Test
    public void parse_markWithInvalidArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("mark blah"));
    }

    @Test
    public void parse_markWithNoArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parse_unmark_success() {
        assertDoesNotThrow(() -> Parser.parse("unmark 1"));
    }

    @Test
    public void parse_unmarkWithInvalidArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("unmark blah"));
    }

    @Test
    public void parse_unmarkWithNoArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void parse_find_success() {
        assertDoesNotThrow(() -> Parser.parse("find abc def"));
    }

    @Test
    public void parse_findWithNoArguments_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("find"));
    }

    @Test
    public void parse_summarise_success() {
        assertDoesNotThrow(() -> Parser.parse("summarise"));
    }
}
