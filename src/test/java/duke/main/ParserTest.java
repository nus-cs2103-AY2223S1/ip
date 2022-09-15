package duke.main;

import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testByeCommand() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testInvalidCommand() {
        try {
            assertEquals(new InvalidCommand(), Parser.parse("32985671"));
        } catch (Exception e) {
            fail();
        }
    }
}
