package parser;

import commands.ByeCommand;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testBye() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
    }
}
