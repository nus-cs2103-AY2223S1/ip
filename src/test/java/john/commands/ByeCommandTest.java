package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class ByeCommandTest {
    @Test
    public void isByeTest() {
        ByeCommand cmd = new ByeCommand();
        assertTrue(ByeCommand.isBye(cmd));
    }

    @Test
    public void executeTest() {
        ByeCommand cmd = new ByeCommand();
        cmd.setData(new TaskList(), new Ui());
        assertEquals("Goodbye!", cmd.execute());
    }
}
