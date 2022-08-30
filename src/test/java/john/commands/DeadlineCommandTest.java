package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class DeadlineCommandTest {
    @Test
    public void executeTest() {
        DeadlineCommand cmd = new DeadlineCommand("hello /by 11/11/2011");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("I've added this task!\n[D][ ] hello (by: Nov 11 2011)\n"
                + "You have 1 task in your list.", cmd.execute());
    }
}

