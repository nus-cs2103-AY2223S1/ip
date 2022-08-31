package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseTaskNumber_success() {
        try {
            assertEquals(1, Parser.parseTaskNumber("mark 2"));
        } catch (DukeException e) {
            fail(); // Should not occur
        }

    }

    @Test
    public void testParseTaskNumber_exceptionThrown() {
        try {
            assertEquals(23, Parser.parseTaskNumber("mark 2e3"));
        } catch (DukeException e) {
            assertEquals("   2e3 is not a valid task number.\n", e.getMessage());
        }

    }
}
