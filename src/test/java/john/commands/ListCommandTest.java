package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class ListCommandTest {
    @Test
    public void executeTestWithoutParams() {
        ListCommand cmd = new ListCommand("");
        TaskList tl = new TaskList();
        cmd.setData(tl);
        assertEquals("|  no tasks found\n", cmd.execute());
    }

    @Test
    public void executeTestWithParams() {
        ListCommand cmd = new ListCommand("10/10/2010");
        TaskList tl = new TaskList();
        tl.addEvent("hello", "10/10/2010");
        cmd.setData(tl);
        assertEquals("1 ==> [E][ ] hello (at: Oct 10 2010)\n", cmd.execute());
    }
}
