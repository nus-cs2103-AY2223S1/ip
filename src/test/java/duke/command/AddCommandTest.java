package duke.command;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {
    
    @Test
    public void execute_deadline_newDeadlineTask() throws DukeException {
        TaskListStub list = new TaskListStub();
        AddCommand command = new AddCommand("deadline", "return book /by 2022-05-03");
        command.execute(list);
        assertTrue(list.checkTask(0, "[D][ ] return book (by: MAY 03 2022)"));
    }
}
