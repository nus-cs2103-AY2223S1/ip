package commands;

import jduke.commands.DeadlineCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void executeTest() {
        DeadlineCommand cmd = new DeadlineCommand("hello /by 11/11/2011");
        cmd.setData(new TaskList());
        assertEquals("|  added task:\n|    [D][ ] hello (by: Nov 11 2011)\n", cmd.execute());
    }
}

