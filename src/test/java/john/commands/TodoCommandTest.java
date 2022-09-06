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
        String sep = System.lineSeparator();
        assertEquals("I've added this task!" + sep + "[T][ ] hello world" + sep
                + "You have 1 task in your list.", cmd.execute());
    }
}

