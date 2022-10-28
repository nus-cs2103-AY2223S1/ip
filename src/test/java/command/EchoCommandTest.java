package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EchoCommandTest {

    @Test
    public void testEchoCommand() {
        EchoCommand command = new EchoCommand("test");
        CommandResult result = new CommandResult("test");
        assertEquals(result.toString(), command.execute().toString());
    }
}
