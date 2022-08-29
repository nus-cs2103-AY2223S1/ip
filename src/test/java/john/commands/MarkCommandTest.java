package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import john.ui.Ui;
import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class MarkCommandTest {
    @Test
    public void executeTest() {
        MarkCommand cmd = new MarkCommand("1");
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        cmd.setData(tl, new Ui());
        assertEquals("|  marked task:\n|    [T][X] hello\n", cmd.execute());
    }
}
