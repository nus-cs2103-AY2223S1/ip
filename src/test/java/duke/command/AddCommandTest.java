package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

class AddCommandTest extends CommandTest {

    @Test
    public void execute_addTodo_success() throws DukeException {
        AddCommand addCommand = new AddCommand(new Todo("test description"));
        testCommandExecution(addCommand);
        assertEquals(INITIAL_TASK_COUNT + 1, getTasks().taskCount());
    }

    @Test
    public void execute_addDeadline_success() throws DukeException {
        AddCommand addCommand = new AddCommand(new Deadline("test", LocalDate.now()));
        testCommandExecution(addCommand);
        assertEquals(INITIAL_TASK_COUNT + 1, getTasks().taskCount());
    }

    @Test
    public void execute_addEvent_success() throws DukeException {
        AddCommand addCommand = new AddCommand(new Event("test description", LocalDate.now()));
        testCommandExecution(addCommand);
        assertEquals(INITIAL_TASK_COUNT + 1, getTasks().taskCount());
    }
}
