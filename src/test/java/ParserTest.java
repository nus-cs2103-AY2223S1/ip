import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ip.utility.Parser;
import ip.command.AddCommand;
import ip.exception.InvalidCommand;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void getCommandTest_blankInput_throwInvalidCommand() {
        parser.loadUserInput("");
        try {
            parser.getCommand();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidCommand);
        }
    }

    @Test
    public void getCommandTest_fakeCommand_throwInvalidCommand() {
        parser.loadUserInput("blah blah blah");
        try {
            parser.getCommand();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidCommand);
        }
    }

    @Test
    public void getCommandTest_addCommand_returnAddCommand() {
        parser.loadUserInput("todo description");
        try {
            assertTrue(parser.getCommand() instanceof AddCommand);
        } catch (Exception e) {
            fail();
        }
    }

}
