package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void executeTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello world");
        DeleteCommand cmd = new DeleteCommand("1");
        cmd.setData(tl, new Ui());
        String sep = System.lineSeparator();
        assertEquals("I've deleted this task!" + sep + "[T][ ] hello world" + sep
                + "You have 0 tasks in your list.", cmd.execute());
    }
}
