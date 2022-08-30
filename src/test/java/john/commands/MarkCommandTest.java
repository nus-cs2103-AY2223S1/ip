package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class MarkCommandTest {
    @Test
    public void executeTest() {
        MarkCommand cmd = new MarkCommand("1");
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        cmd.setData(tl, new Ui());
        assertEquals("I've marked this task as complete!\n[T][X] hello", cmd.execute());
    }
}
