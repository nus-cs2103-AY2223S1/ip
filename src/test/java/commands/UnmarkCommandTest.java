package commands;

import jduke.commands.UnmarkCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {
    @Test
    public void executeTest() {
        UnmarkCommand cmd = new UnmarkCommand("1");
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        cmd.setData(tl);
        assertEquals("|  unmarked task:\n|    [T][ ] hello\n", cmd.execute());
    }
}
