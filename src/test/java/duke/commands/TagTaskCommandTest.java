package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TagTaskCommandTest {
    private static TaskList getTaskList() {
        return new TaskList(new ArrayList<>() {{
            add(new Todo("Test", false));
        }});
    }

    @Test
    public void execution_invalidIndex_exceptionThrown() {

        TagTaskCommand command = new TagTaskCommand(1, "IGNORE_ME");

        assertThrows(NoSuchTaskException.class,
                () -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_exceptionNotThrown() {
        TagTaskCommand command = new TagTaskCommand(0, "IGNORE_ME");

        assertDoesNotThrow(() -> command.execute(getTaskList(), new Storage("")));
    }

    @Test
    public void execution_validIndex_singleTaskRemoved() throws NoSuchTaskException {
        String tag = "fun";
        TagTaskCommand command = new TagTaskCommand(0, tag);
        TaskList taskList = getTaskList();

        assertEquals(taskList.get(0).getTags().contains(tag), false);

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.get(0).getTags().contains(tag), true);
    }
}
