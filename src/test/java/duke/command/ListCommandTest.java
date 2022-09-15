package duke.command;

import duke.Ui;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void isExitTest() {
        Command cmd = new Command() {
            @Override
            public void execute(TaskList taskList, Ui ui) {

            }
        }
        assertEquals(cmd.isExit, true);
    }
}
