package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class ParserTest {

    @Test
    public void correctParseDeadline() {
        String[] test = Parser.parseInput("deadline read book /by 2022-08-10 18:00");
        String[] expected = new String[] {"deadline", "read book", "2022-08-10 18:00"};
        assertEquals(test[0], expected[0]);
        assertEquals(test[1], expected[1]);
        assertEquals(test[2], expected[2]);
    }

    @Test
    public void wrongInput_exceptionThrown() {
        assertThrows(InvalidTaskException.class, () -> Parser.parseInput("invalid input"));
    }

    @Test
    public void wrongFormat_exceptionThrown() {
        assertThrows(InvalidFormatException.class, () -> Parser.parseInput("deadline read book 2000-01-01 10:10"));
    }


}
