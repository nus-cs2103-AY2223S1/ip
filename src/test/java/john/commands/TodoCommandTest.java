package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import john.ui.Ui;
import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class TodoCommandTest {
    @Test
    public void executeTest() {
        TodoCommand cmd = new TodoCommand("hello world");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("|  added task:\n|    [T][ ] hello world\n", cmd.execute());
    }
}

