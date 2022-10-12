package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AddTodoCommandTest {
    private static final String TODO_DESCRIPTION = "todo Finish Assignment";
    private static final String TODO_DESCRIPTION_INVALID = "todo  ";
    private static Storage storage;
    private static TaskList tasks;

    @BeforeAll
    public static void createFile() {
        storage = new Storage(Paths.get("dukeList_Test.txt"));
        tasks = new TaskList(new ArrayList<>(), storage);
    }

    @Test
    public void execute_addToDoCommand_success() throws DukeException {
        AddTodoCommand command = new AddTodoCommand(TODO_DESCRIPTION.split(" "));
        String output = "Got it! I have added this task to your list:\n  "
                + "[T][ ] Finish Assignment"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(output, command.execute(storage, tasks));
    }

    @Test
    public void execute_addToDoCommand_emptyDescription_throwsException() {
        AddTodoCommand command = new AddTodoCommand(TODO_DESCRIPTION_INVALID.split(" "));
        assertThrows(DukeException.class, () -> command.execute(storage, tasks));
    }
}
