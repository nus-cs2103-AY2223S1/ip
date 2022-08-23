package blink.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {

    @Test
    public void executeTest() {
        Command byeCommand = new ByeCommand();
        assertEquals(true, byeCommand.isExit());
    }
}
