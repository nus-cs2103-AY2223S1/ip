package commands;

import jduke.commands.ByeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {
    @Test
    public void isByeTest() {
        ByeCommand cmd = new ByeCommand();
        assertTrue(ByeCommand.isBye(cmd));
    }

    @Test
    public void executeTest() {
        ByeCommand cmd = new ByeCommand();
        assertEquals("", cmd.execute());
    }
}
