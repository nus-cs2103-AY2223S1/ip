package rick;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import rick.commands.ListCommand;

public class ParserTest {
    @Test
    public void parseCommand_invalidInput_throwsAssertionError() throws AssertionError {
        assertThrows(AssertionError.class, () -> Parser.parseCommand(null));
    }

    @Test
    public void parseCommand_InvalidCommand_throwsRickException() throws RickException {
        assertThrows(RickException.class, () -> Parser.parseCommand("test"));
    }

    @Test
    public void parseCommand_validListCommand_success() throws RickException {
        assertDoesNotThrow(() -> Parser.parseCommand("list"));
        assertEquals(Parser.parseCommand("list"), new ListCommand(""));
    }
}
