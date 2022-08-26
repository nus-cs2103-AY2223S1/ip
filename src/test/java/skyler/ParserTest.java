package skyler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = new Parser(new TaskList(new Storage("test")));
        try {
            assertEquals(1, parser.parse("deadline CS2103 iP /by 2022-08-25 2359"));
        } catch (SkylerException e) {
            fail(); // the test should not reach this line
        }
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        Parser parser = new Parser(new TaskList(new Storage("test")));
        try {
            assertEquals(1, parser.parse("todo "));
            fail(); // the test should not reach this line
        } catch (SkylerException e) {
            assertEquals("No input task", e.getMessage());
        }
    }
}
