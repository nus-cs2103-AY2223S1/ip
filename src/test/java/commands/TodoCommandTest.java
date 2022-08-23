package commands;

import jduke.commands.TodoCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void executeTest() {
        TodoCommand cmd = new TodoCommand("hello world");
        cmd.setData(new TaskList());
        assertEquals("|  added task:\n|    [T][ ] hello world\n", cmd.execute());
    }
}

