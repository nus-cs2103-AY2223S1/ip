package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void wrongCommandTest() {
        Exception exception = assertThrows(DukeException.class,
                                           () -> Parser.parse("idk"));
        assertEquals("I don't know this command!", exception.getMessage());
    }

}
