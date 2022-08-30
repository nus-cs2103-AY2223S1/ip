package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class UnmarkCommandTest {
    @Test
    public void executeTest() {
        UnmarkCommand cmd = new UnmarkCommand("1");
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        cmd.setData(tl, new Ui());
        assertEquals("I've unmarked this task!\n[T][ ] hello", cmd.execute());
    }
}
