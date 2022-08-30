package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class TodoCommandTest {
    @Test
    public void executeTest() {
        TodoCommand cmd = new TodoCommand("hello world");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("I've added this task!\n[T][ ] hello world\n"
                + "You have 1 task in your list.", cmd.execute());
    }
}

