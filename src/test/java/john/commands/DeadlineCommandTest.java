package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import john.ui.Ui;
import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class DeadlineCommandTest {
    @Test
    public void executeTest() {
        DeadlineCommand cmd = new DeadlineCommand("hello /by 11/11/2011");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("|  added task:\n|    [D][ ] hello (by: Nov 11 2011)\n", cmd.execute());
    }
}

