package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

/*import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;*/

public class TodoCommandTest {

    @Test
    public void isExitTest() {
        TodoCommand command = new TodoCommand("task1");
        assertEquals(command.isExit(), false);
    }
}
