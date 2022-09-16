package duke.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;

/**
 * Represents a test class for the AddDeadlineCommand class.
 */
public class AddDeadlineCommandTest {
    @Test
    public void execute_deadline_newDeadlineTask() throws DukeException {
        TaskListStub list = new TaskListStub();
        AddDeadlineCommand command = new AddDeadlineCommand("return book /by 2022-05-03");
        command.execute(list);
        assertTrue(list.checkTask(0, "[D][ ] return book (by: MAY 03 2022)"));
    }
}
