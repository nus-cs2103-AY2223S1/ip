package commands;

import jduke.commands.FindCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    @Test
    public void executeTestNoResults() {
        FindCommand cmd = new FindCommand("hello");
        TaskList tl = new TaskList();
        cmd.setData(tl);
        assertEquals("|  no tasks found\n", cmd.execute());
    }

    @Test
    public void executeTest() {
        FindCommand cmd = new FindCommand("hello");
        TaskList tl = new TaskList();
        cmd.setData(tl);
        tl.addTodo("hello world");
        tl.addTodo("goodbye");
        tl.addTodo("~~ hello ~~");
        assertEquals("1 ==> [T][ ] hello world\n3 ==> [T][ ] ~~ hello ~~\n", cmd.execute());
    }
}
