package Duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Duke.Commands byeCommand = Duke.Commands.BYE;
    private Duke.Commands listCommand = Duke.Commands.LIST;
    private Duke.Commands markCommand = Duke.Commands.MARK;
    private Parser parser = new Parser();

    @Test
    @DisplayName("Test parseCommand")
    public void testParseCommand() {
        assertEquals(byeCommand, parser.parseCommand("ByE"));
        assertEquals(listCommand, parser.parseCommand(" lISt  "));
        assertEquals(markCommand, parser.parseCommand(" mark    "));
    }
}
