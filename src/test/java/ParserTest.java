import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import ip.command.DukeCommand;
import org.junit.jupiter.api.Test;

import ip.utility.Parser;
import ip.command.ByeCommand;
import ip.command.ListCommand;
import ip.exception.InvalidCommand;



public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void getCommandTest_blankInput_returnByeCommand() {
        parser.load("");
        try {
            DukeCommand command = parser.getCommand();
            assertTrue(command instanceof ByeCommand);
        } catch (InvalidCommand e) {
            fail();
        }
    }

    @Test
    public void getCommandTest_listCommand_returnListCommand() {
        parser.load("list");
        try {
            DukeCommand command = parser.getCommand();
            assertTrue(command instanceof ListCommand);
        } catch (InvalidCommand e) {
            fail();
        }
    }
}
