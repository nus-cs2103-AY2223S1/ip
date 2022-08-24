package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExitCommandTest {

    @Test
    public void isExit_returnsTrue() {
        ExitCommand command = new ExitCommand();
        assertEquals(command.isExit(), true);
    }
}
