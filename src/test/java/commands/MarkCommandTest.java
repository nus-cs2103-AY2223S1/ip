package commands;

import jduke.commands.MarkCommand;
import jduke.data.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    @Test
    public void executeTest() {
        MarkCommand cmd = new MarkCommand("1");
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        cmd.setData(tl);
        assertEquals("|  marked task:\n|    [T][X] hello\n", cmd.execute());
    }
}
