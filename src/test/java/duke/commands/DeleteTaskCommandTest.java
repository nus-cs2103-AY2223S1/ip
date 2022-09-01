package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskCommandTest {
    private static TaskList getTaskList() {
        return new TaskList(new ArrayList<>() {{
            add(new Todo("Test"));
        }});
    }

    @Test
    public void execution_invalidIndex_exceptionThrown() {

        DeleteTaskCommand command = new DeleteTaskCommand(1);

        assertThrows(NoSuchTaskException.class,
                () -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_exceptionNotThrown() {
        DeleteTaskCommand command = new DeleteTaskCommand(0);

        assertDoesNotThrow(() -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_singleTaskRemoved() throws NoSuchTaskException {

        DeleteTaskCommand command = new DeleteTaskCommand(0);
        TaskList taskList = getTaskList();

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.getNumTasks(), 0);
    }
}
