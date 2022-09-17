package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnmarkCommandTest {
    private static TaskList getTaskList() {
        return new TaskList(new ArrayList<>() {{
            add(new Todo("Test", true));
        }});
    }

    @Test
    public void execution_invalidIndex_exceptionThrown() {

        UnmarkCommand command = new UnmarkCommand(1);

        assertThrows(NoSuchTaskException.class,
                () -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_exceptionNotThrown() {
        UnmarkCommand command = new UnmarkCommand(0);

        assertDoesNotThrow(() -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_singleTaskRemoved() throws NoSuchTaskException {

        UnmarkCommand command = new UnmarkCommand(0);
        TaskList taskList = getTaskList();

        assertEquals(taskList.get(0).isCompleted(), true);

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.get(0).isCompleted(), false);
    }
}
