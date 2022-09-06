package blink.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {

    @Test
    public void executeTest() {
        Command byeCommand = new ByeCommand();
        assertEquals(true, byeCommand.isExit());
    }
}
