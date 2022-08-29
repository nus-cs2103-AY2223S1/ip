package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IncorrectCommandTest {

    @Test
    public void executeTest() {
        IncorrectCommand cmd = new IncorrectCommand("hello");
        assertEquals("hello", cmd.execute());
    }
}
