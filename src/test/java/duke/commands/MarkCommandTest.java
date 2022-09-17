package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
    private static TaskList getTaskList() {
        return new TaskList(new ArrayList<>() {{
            add(new Todo("Test", false));
        }});
    }

    @Test
    public void execution_invalidIndex_exceptionThrown() {

        MarkCommand command = new MarkCommand(1);

        assertThrows(NoSuchTaskException.class,
                () -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_exceptionNotThrown() {
        MarkCommand command = new MarkCommand(0);

        assertDoesNotThrow(() -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_singleTaskRemoved() throws NoSuchTaskException {

        MarkCommand command = new MarkCommand(0);
        TaskList taskList = getTaskList();

        assertEquals(taskList.get(0).isCompleted(), false);

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.get(0).isCompleted(), true);
    }
}
