package commands;

import jduke.commands.DeleteCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello world");
        DeleteCommand cmd = new DeleteCommand("1");
        cmd.setData(tl);
        assertEquals("|  deleted task:\n|    [T][ ] hello world\n", cmd.execute());
    }
}
