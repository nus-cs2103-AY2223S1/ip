package chacha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_nullDelete_exception() {
        try {
            Parser.parse("delete");
            fail("Expected exception was not thrown");
        } catch (ChachaException e) {
            assertEquals("Chacha Error: Please enter valid task number to delete.", e.getMessage());
        }
    }

    @Test
    public void parse_nullMark_exception() {
        try {
            Parser.parse("mark");
            fail("Expected exception was not thrown");
        } catch (ChachaException e) {
            assertEquals("Chacha Error: Invalid input. Please try again!", e.getMessage());
        }
    }

    @Test
    public void parse_nullUnmark_exception() {
        try {
            Parser.parse("unmark");
            fail("Expected exception was not thrown");
        } catch (ChachaException e) {
            assertEquals("Chacha Error: Please enter valid task number to unmark.", e.getMessage());
        }
    }
}
