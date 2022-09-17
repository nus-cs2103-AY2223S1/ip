package duke.commands;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CreateDeadlineCommandTest {

    @Test
    public void execution_addsSingleItem()  {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Deadline deadline = new Deadline("Test", LocalDate.now());
        CreateDeadlineCommand command = new CreateDeadlineCommand(deadline);

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.getNumTasks(), 1);
    }

    @Test
    public void execution_addsCreatedDeadline()  {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Deadline deadline = new Deadline("Test", LocalDate.now());
        CreateDeadlineCommand command = new CreateDeadlineCommand(deadline);

        command.execute(taskList, new Storage(""));

        assertDoesNotThrow(() -> assertEquals(taskList.get(0), deadline));
    }
}
