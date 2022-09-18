package bobby.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.testng.annotations.Test;

import bobby.Bobby;
import bobby.Parser;
import bobby.exceptions.DukeException;


public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void correctCommandTest() throws DukeException {
        Bobby.Commands command = Bobby.Commands.EVENT;
        assertEquals(command, parser.analyzeCommand("event"));
    }
    @Test
    public void wrongCommandTest() {
        assertThrows(DukeException.class, () -> parser.analyzeCommand("abcd"));
    }
}
