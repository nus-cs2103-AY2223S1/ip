package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class ListCommandTest {
    @Test
    public void execute_noListParams_noTaskReturned() {
        ListCommand cmd = new ListCommand("");
        TaskList tl = new TaskList();
        cmd.setData(tl, new Ui());
        assertEquals("There are no tasks in your list.", cmd.execute());
    }

    @Test
    public void execute_listParams_taskReturned() {
        ListCommand cmd = new ListCommand("10/10/2010");
        TaskList tl = new TaskList();
        tl.addEvent("hello", "10/10/2010");
        cmd.setData(tl, new Ui());
        assertEquals("1. [E][ ] hello (at: Oct 10 2010)", cmd.execute());
    }
}
