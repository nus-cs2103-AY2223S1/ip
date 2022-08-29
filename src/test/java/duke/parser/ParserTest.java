package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.OnDateCommand;

public class ParserTest {

    @Test
    public void testByeCommand() {
        try {
            assertEquals(false, Parser.parse("bye").isRunning());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testOnCommand() {
        try {
            assertTrue(Parser.parse("on 2022-10-15") instanceof OnDateCommand);
        } catch (Exception e) {
            fail();
        }
    }

}
