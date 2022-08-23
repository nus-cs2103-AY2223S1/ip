package commands;

import jduke.commands.EventCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    @Test
    public void executeTest() {
        EventCommand cmd = new EventCommand("hello /at 11/11/2011");
        cmd.setData(new TaskList());
        assertEquals("|  added task:\n|    [E][ ] hello (at: Nov 11 2011)\n", cmd.execute());
    }
}

