package duke.parser;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_incorrectInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("bye bye"));
        assertThrows(DukeException.class, () -> Parser.parse(""));
        assertThrows(DukeException.class, () -> Parser.parse(" "));
        assertThrows(DukeException.class, () -> Parser.parse("list list"));
        assertThrows(DukeException.class, () -> Parser.parse(""));
        assertThrows(DukeException.class, () -> Parser.parse("bye bye"));
        assertThrows(DukeException.class, () -> Parser.parse("event /at "));
        assertThrows(DukeException.class, () -> Parser.parse("event /at school"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline assignment /by tomorrow"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline assignment /by 11-11-2022"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline /by 2022-11-11"));
        assertThrows(DukeException.class, () -> Parser.parse("todo "));
        assertThrows(DukeException.class, () -> Parser.parse("mark this"));
        assertThrows(DukeException.class, () -> Parser.parse("mark "));
        assertThrows(DukeException.class, () -> Parser.parse("unmark this"));
        assertThrows(DukeException.class, () -> Parser.parse("unmark "));
        assertThrows(DukeException.class, () -> Parser.parse("delete this"));
        assertThrows(DukeException.class, () -> Parser.parse("delete "));
    }
}
