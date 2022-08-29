package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import john.ui.Ui;
import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class DeleteCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello world");
        DeleteCommand cmd = new DeleteCommand("1");
        cmd.setData(tl, new Ui());
        assertEquals("|  deleted task:\n|    [T][ ] hello world\n", cmd.execute());
    }
}
