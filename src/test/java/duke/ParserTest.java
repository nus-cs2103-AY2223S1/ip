package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getKeyword_notKeyword_exceptionThrown() {
        Parser parser = new Parser();
        try {
            assertEquals("wrong", parser.getKeyword("wrong"));
            fail();
        } catch (DukeException e) {
            assertEquals("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
