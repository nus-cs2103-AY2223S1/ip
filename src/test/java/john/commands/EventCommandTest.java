package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class EventCommandTest {
    @Test
    public void executeTest() {
        EventCommand cmd = new EventCommand("hello /at 11/11/2011");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("I've added this task!\n[E][ ] hello (at: Nov 11 2011)\n"
                + "You have 1 task in your list.", cmd.execute());
    }
}

