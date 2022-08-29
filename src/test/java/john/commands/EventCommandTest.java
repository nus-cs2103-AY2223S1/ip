package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import john.ui.Ui;
import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class EventCommandTest {
    @Test
    public void executeTest() {
        EventCommand cmd = new EventCommand("hello /at 11/11/2011");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("|  added task:\n|    [E][ ] hello (at: Nov 11 2011)\n", cmd.execute());
    }
}

