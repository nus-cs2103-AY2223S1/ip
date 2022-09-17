import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.FindTaskCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class FindTaskCommandTest {
    @Test
    public void executeFindTaskCommand_matchExists_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        ToDo matchingToDo = new ToDo("abc keywordishere def");
        Deadline matchingDeadline = new Deadline("abc keyword is here def", LocalDateTime.now());
        Event matchingEvent = new Event("keyword", LocalDateTime.now(), LocalDateTime.now());
        taskList.addTask(matchingToDo);
        taskList.addTask(matchingDeadline);
        taskList.addTask(matchingEvent);

        FindTaskCommand findTaskCommand = new FindTaskCommand("keyword");
        Response<TaskList> foundTasksResponse = findTaskCommand.execute(taskList, storage);

        assertEquals(ResponseType.LIST, foundTasksResponse.getResponseType());
        assertEquals("sure thing! here are the matching tasks bobo found",
                foundTasksResponse.getResponseMessage());
        assertEquals(3, foundTasksResponse.getResponseObject().size());
        assertEquals(matchingToDo, foundTasksResponse.getResponseObject().getTask(1));
        assertEquals(matchingDeadline, foundTasksResponse.getResponseObject().getTask(2));
        assertEquals(matchingEvent, foundTasksResponse.getResponseObject().getTask(3));
    }

    @Test
    public void executeFindTaskCommand_noMatchExistsDate_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        ToDo matchingToDo = new ToDo("abc nokeywordishere def");
        Deadline matchingDeadline = new Deadline("abc no keyword is here def", LocalDateTime.now());
        Event matchingEvent = new Event("no keyword", LocalDateTime.now(), LocalDateTime.now());
        taskList.addTask(matchingToDo);
        taskList.addTask(matchingDeadline);
        taskList.addTask(matchingEvent);

        FindTaskCommand findTaskCommand = new FindTaskCommand("     ");
        Response<TaskList> foundTasksResponse = findTaskCommand.execute(taskList, storage);

        assertEquals(ResponseType.ERROR, foundTasksResponse.getResponseType());
        assertEquals("uh-oh. bobo can't find any matching tasks in your list...",
                foundTasksResponse.getResponseMessage());
        assertEquals(null, foundTasksResponse.getResponseObject());
    }
}
