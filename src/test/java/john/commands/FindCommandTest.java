package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class FindCommandTest {
    @Test
    public void executeTestNoResults() {
        FindCommand cmd = new FindCommand("hello");
        TaskList tl = new TaskList();
        cmd.setData(tl, new Ui());
        assertEquals("There are no tasks in your list.", cmd.execute());
    }

    @Test
    public void executeTest() {
        FindCommand cmd = new FindCommand("hello");
        TaskList tl = new TaskList();
        cmd.setData(tl, new Ui());
        tl.addTodo("hello world");
        tl.addTodo("goodbye");
        tl.addTodo("~~ hello ~~");
        assertEquals("1. [T][ ] hello world\n3. [T][ ] ~~ hello ~~\n", cmd.execute());
    }
}
