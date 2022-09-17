import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.AddTaskCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class AddTaskCommandTest {
    @Test
    public void constructExecuteToDo_validInput_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        AddTaskCommand addToDoTask = new AddTaskCommand("todo description 123");
        Response<Task> addedToDoTask = addToDoTask.execute(taskList, storage);
        ToDo expectedToDo = new ToDo("description 123");
        // assert the response is correct
        assertEquals(ResponseType.TASK, addedToDoTask.getResponseType());
        assertEquals("aye aye! bobo added this task ( ･o･)"
                + " you now have 1 items in your list!", addedToDoTask.getResponseMessage());
        assertEquals(expectedToDo.toString(), addedToDoTask.getResponseObject().toString());
        // assert the task list is correct
        assertEquals(1, taskList.size());
        assertEquals(expectedToDo.toString(), taskList.getTask(1).toString());
    }

    @Test
    public void constructExecuteDeadline_validInput_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        AddTaskCommand addDeadlineTask = new AddTaskCommand("deadline description 123 /by 21/09/22 9am");
        Response<Task> addedDeadlineTask = addDeadlineTask.execute(taskList, storage);

        LocalDateTime dueDate = LocalDateTime.of(2022, 9, 21, 9, 0);
        Deadline expectedDeadline = new Deadline("description 123", dueDate);
        // assert the response is correct
        assertEquals(ResponseType.TASK, addedDeadlineTask.getResponseType());
        assertEquals("aye aye! bobo added this task ( ･o･)"
                + " you now have 1 items in your list!", addedDeadlineTask.getResponseMessage());
        assertEquals(expectedDeadline.toString(), addedDeadlineTask.getResponseObject().toString());
        // assert the task list is correct
        assertEquals(1, taskList.size());
        assertEquals(expectedDeadline.toString(), taskList.getTask(1).toString());
    }

    @Test
    public void constructExecuteEvent_validInput_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        AddTaskCommand addEventTask = new AddTaskCommand("event description 123 /at 21/09/22 9am"
                + " /to 21/09/22 10am");
        Response<Task> addedEventTask = addEventTask.execute(taskList, storage);

        LocalDateTime startTime = LocalDateTime.of(2022, 9, 21, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 9, 21, 10, 0);
        Event expectedEvent = new Event("description 123", startTime, endTime);
        // assert the response is correct
        assertEquals(ResponseType.TASK, addedEventTask.getResponseType());
        assertEquals("aye aye! bobo added this task ( ･o･)"
                + " you now have 1 items in your list!", addedEventTask.getResponseMessage());
        assertEquals(expectedEvent.toString(), addedEventTask.getResponseObject().toString());
        // assert the task list is correct
        assertEquals(1, taskList.size());
        assertEquals(expectedEvent.toString(), taskList.getTask(1).toString());
    }
}
