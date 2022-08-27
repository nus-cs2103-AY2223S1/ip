package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.StorageStub;
import duke.TaskList;
import duke.task.Task;
import duke.task.Todo;

public class CommandTest {
    private final StorageStub storageStub = new StorageStub(List.<Task>of());
    private final TaskList taskList = new TaskList();

    @Test
    void execute_addCommand() throws DukeException {
        assertEquals(storageStub.getTasks().size(), 0);

        Task task = new Todo("Test task");
        Command command = new AddCommand(task);
        command.execute();

        assertEquals(storageStub.getTasks().size(), 1);
        assertEquals(taskList.size(), 1);
    }

    @Test
    void execute_listCommand() throws DukeException {
        Command.setTaskList(new TaskList(List.of()));

        Command command = new ListCommand();
        String response = command.execute();
        String expected = "";

        assertEquals("", response);

        Task todo = new Todo("Test task");
        Command.setTaskList(new TaskList(List.of(todo)));
        response = command.execute();

        expected = "1. " + todo;
        assertEquals(expected, response);
    }

    @Test
    void execute_findCommand() throws DukeException {
        Task todo = new Todo("Test task");
        Command.setTaskList(new TaskList(List.of(todo)));

        Command command = new FindCommand("Test");
        String response = command.execute();

        String expected = "Here is what I found:" + "\n"
                + "1. " + todo;
        assertEquals(expected, response);

        command = new FindCommand("bad");
        response = command.execute();
        expected = "No task matched your query!";
        assertEquals(expected, response);
    }
}
