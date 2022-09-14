package duke.Test;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchCommandException;
import duke.Parser;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void correctCommandTest() throws DukeException {
        Duke.Commands command = Duke.Commands.EVENT;
        assertEquals(command, parser.analyzeCommand("event"));
    }
    @Test
    public void wrongCommandTest() {
        assertThrows(NoSuchCommandException.class, () -> parser.analyzeCommand("abcd"));
    }
}
