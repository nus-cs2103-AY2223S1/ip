package duke.parser;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void testParse_byeCommand_success() {
        try {
            Parser.parse("mark");
            fail();
        } catch (DukeException e) {
            assertEquals("Task number cannot be empty.", e.getMessage());
        }
    }

}
