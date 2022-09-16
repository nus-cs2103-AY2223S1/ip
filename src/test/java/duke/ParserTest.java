package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.command.Parser;
import duke.exception.DukeException;



public class ParserTest {
    @Test
    public void wrongCommandTest() {
        Exception exception = assertThrows(DukeException.class, () -> Parser.parse("idk"));
        assertEquals("I don't know this command!", exception.getMessage());
    }

}
