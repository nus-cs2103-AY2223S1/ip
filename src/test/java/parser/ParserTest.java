package parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.ByeCommand;
import exceptions.DukeException;

public class ParserTest {
    @Test
    public void testBye() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ByeCommand);
    }
}
