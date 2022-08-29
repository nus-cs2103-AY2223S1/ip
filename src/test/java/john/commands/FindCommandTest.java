package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import john.ui.Ui;
import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class FindCommandTest {
    @Test
    public void executeTestNoResults() {
        FindCommand cmd = new FindCommand("hello");
        TaskList tl = new TaskList();
        cmd.setData(tl, new Ui());
        assertEquals("|  no tasks found\n", cmd.execute());
    }

    @Test
    public void executeTest() {
        FindCommand cmd = new FindCommand("hello");
        TaskList tl = new TaskList();
        cmd.setData(tl, new Ui());
        tl.addTodo("hello world");
        tl.addTodo("goodbye");
        tl.addTodo("~~ hello ~~");
        assertEquals("1 ==> [T][ ] hello world\n3 ==> [T][ ] ~~ hello ~~\n", cmd.execute());
    }
}
