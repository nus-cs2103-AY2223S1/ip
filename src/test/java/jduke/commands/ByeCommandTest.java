package jduke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
