package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {
    @Test
    public void isExit_defaultIsExitValue_success() {
        assertEquals(false, new CommandStub().isExit());
    }
}