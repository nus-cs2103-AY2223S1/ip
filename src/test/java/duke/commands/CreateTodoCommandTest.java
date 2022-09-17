package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTodoCommandTest {

    @Test
    public void execution_addsSingleItem() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Todo todo = new Todo("Test");
        CreateTodoCommand command = new CreateTodoCommand(todo);

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.getNumTasks(), 1);
    }

    @Test
    public void execution_addsCreatedEvent() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Todo todo = new Todo("Test");
        CreateTodoCommand command = new CreateTodoCommand(todo);

        command.execute(taskList, new Storage(""));

        assertDoesNotThrow(() -> assertEquals(taskList.get(0), todo));
    }
}
