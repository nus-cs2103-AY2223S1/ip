import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.EditTaskCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class EditTaskCommandTest {
    @Test
    public void editTask_toDoTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        ToDo toDo = new ToDo("description");
        taskList.addTask(toDo);

        EditTaskCommand editTaskCommand = new EditTaskCommand("1 edited abc!");
        Response<Task> editedResponse = editTaskCommand.execute(taskList, storage);

        assertEquals(ResponseType.TASK, editedResponse.getResponseType());
        assertEquals("gotcha! bobo edited this task, ta-da!\n * * (•◡•) / * *",
                editedResponse.getResponseMessage());
        assertEquals("[T][ ] edited abc!", editedResponse.getResponseObject().toString());
        assertEquals("[T][ ] edited abc!", taskList.getTask(1).toString());
    }

    @Test
    public void editTask_deadlineTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        LocalDateTime dueDate = LocalDateTime.of(2022, 9, 21, 9, 0);
        Deadline deadline = new Deadline("description 123", dueDate);
        taskList.addTask(deadline);

        EditTaskCommand editTaskCommand = new EditTaskCommand("1 edited deadline!! /by 22/09/22 1:00");
        Response<Task> editedResponse = editTaskCommand.execute(taskList, storage);

        assertEquals(ResponseType.TASK, editedResponse.getResponseType());
        assertEquals("gotcha! bobo edited this task, ta-da!\n * * (•◡•) / * *",
                editedResponse.getResponseMessage());
        assertEquals("[D][ ] edited deadline!! (by: Thursday, 22 Sep 2022 01:00)",
                editedResponse.getResponseObject().toString());
        assertEquals("[D][ ] edited deadline!! (by: Thursday, 22 Sep 2022 01:00)",
                taskList.getTask(1).toString());
    }

    @Test
    public void editTask_eventTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");

        LocalDateTime startTime = LocalDateTime.of(2022, 9, 21, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 9, 21, 10, 0);
        Event event = new Event("description 123", startTime, endTime);
        taskList.addTask(event);

        EditTaskCommand editTaskCommand = new EditTaskCommand("1 edited event /at 21/09/22 8:00"
                + " /to 22.09.22 10am");
        Response<Task> editedResponse = editTaskCommand.execute(taskList, storage);

        assertEquals(ResponseType.TASK, editedResponse.getResponseType());
        assertEquals("gotcha! bobo edited this task, ta-da!\n * * (•◡•) / * *",
                editedResponse.getResponseMessage());
        assertEquals("[E][ ] edited event (at: Wednesday, 21 Sep 2022 08:00 to Thursday, 22 Sep 2022 10:00)",
                editedResponse.getResponseObject().toString());
        assertEquals("[E][ ] edited event (at: Wednesday, 21 Sep 2022 08:00 to Thursday, 22 Sep 2022 10:00)",
                taskList.getTask(1).toString());
    }

    @Test
    public void editTask_eventTaskStartAfterEnd_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            Storage storage = new Storage("data", "duke.txt");

            LocalDateTime startTime = LocalDateTime.of(2022, 9, 21, 9, 0);
            LocalDateTime endTime = LocalDateTime.of(2022, 9, 21, 10, 0);
            Event event = new Event("description 123", startTime, endTime);
            taskList.addTask(event);

            EditTaskCommand editTaskCommand = new EditTaskCommand("1 edited event /at 21/09/22 12:00"
                    + " /to 21.09.22 10am");
            editTaskCommand.execute(taskList, storage);
        } catch (DukeException e) {
            assertEquals("Start datetime 2022-09-21T12:00 cannot be after end datetime 2022-09-21T10:00",
                    e.getMessage());
        }
    }
}
