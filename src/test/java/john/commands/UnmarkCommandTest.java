package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;

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
