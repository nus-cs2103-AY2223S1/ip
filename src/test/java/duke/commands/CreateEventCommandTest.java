package duke.commands;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CreateEventCommandTest {

    @Test
    public void execution_addsSingleItem() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Event event = new Event("Test", LocalDate.now());
        CreateEventCommand command = new CreateEventCommand(event);

        command.execute(taskList, new Storage(""));

        assertEquals(taskList.getNumTasks(), 1);
    }

    @Test
    public void execution_addsCreatedEvent() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Event event = new Event("Test", LocalDate.now());
        CreateEventCommand command = new CreateEventCommand(event);

        command.execute(taskList, new Storage(""));

        assertDoesNotThrow(() -> assertEquals(taskList.get(0), event));
    }
}
