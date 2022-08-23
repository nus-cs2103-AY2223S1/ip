package commands;

import jduke.commands.IncorrectCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncorrectCommandTest {
    @Test
    public void executeTestWithoutParams() {
        IncorrectCommand cmd = new IncorrectCommand();
        assertEquals("|  cannot understand command\n", cmd.execute());
    }

    @Test
    public void executeTestWithParams() {
        IncorrectCommand cmd = new IncorrectCommand("hello");
        assertEquals("hello", cmd.execute());
    }
}
