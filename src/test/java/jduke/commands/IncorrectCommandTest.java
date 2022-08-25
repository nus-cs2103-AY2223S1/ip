package jduke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
