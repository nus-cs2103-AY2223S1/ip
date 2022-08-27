package roofus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    @Test
    public void isRunningTest() {
        Command c = new ByeCommand();
        assertEquals(false, c.isRunning());
    }
}
