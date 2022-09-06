package gibson.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getTrailingInt_inputWithTrailingInt_success() {
        assertEquals(12345, Parser.getTrailingInt("Test12345"));
        assertEquals(45, Parser.getTrailingInt("Test123Test45"));
    }

    @Test
    public void getTrailingInt_inputWithoutTrailingInt_exceptionThrown() {
        try {
            Parser.getTrailingInt("TEST");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("String is invalid", e.getMessage());
        }
        try {
            Parser.getTrailingInt("TEST123T");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("String is invalid", e.getMessage());
        }
    }
}
