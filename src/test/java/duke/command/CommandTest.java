package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommandTest {
    @Test
    public void isExit_defaultIsExitValue_success() {
        assertEquals(false, new CommandStub().isExit());
    }
}
