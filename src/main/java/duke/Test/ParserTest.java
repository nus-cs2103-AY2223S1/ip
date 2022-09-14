package duke.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.testng.annotations.Test;

import duke.Duke;
import duke.Parser;
import duke.exceptions.DukeException;


public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void correctCommandTest() throws DukeException {
        Duke.Commands command = Duke.Commands.EVENT;
        assertEquals(command, parser.analyzeCommand("event"));
    }
    @Test
    public void wrongCommandTest() {
        assertThrows(DukeException.class, () -> parser.analyzeCommand("abcd"));
    }
}
