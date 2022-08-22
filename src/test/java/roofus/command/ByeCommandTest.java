package roofus.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void isRunningTest() {
        Command c = new ByeCommand();
        assertEquals(false, c.isRunning());
    }
}
