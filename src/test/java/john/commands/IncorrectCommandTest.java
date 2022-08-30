package john.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;
import john.ui.Ui;

public class IncorrectCommandTest {

    @Test
    public void executeTest() {
        IncorrectCommand cmd = new IncorrectCommand("hello");
        cmd.setData(new TaskList(), new Ui());
        assertEquals("I cannot understand 'hello'. Try another command!", cmd.execute());
    }
}
