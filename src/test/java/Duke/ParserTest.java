package Duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test Parser Class.
 */
public class ParserTest {
    /**
     * Bye Command Object.
     */
    private final Duke.Commands byeCommand = Duke.Commands.BYE;

    /**
     * List Command Object.
     */
    private final Duke.Commands listCommand = Duke.Commands.LIST;

    /**
     * Mark Command Object.
     */
    private final Duke.Commands markCommand = Duke.Commands.MARK;

    /**
     * Parser object.
     */
    private final Parser parser = new Parser();

    /**
     * To test parseCommand().
     */
    @Test
    @DisplayName("Test parseCommand")
    public void testParseCommand() {
        assertEquals(byeCommand, parser.parseCommand("ByE"));
        assertEquals(listCommand, parser.parseCommand(" lISt  "));
        assertEquals(markCommand, parser.parseCommand(" mark    "));
    }
}
