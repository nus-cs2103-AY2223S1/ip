import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.MarkTaskDoneCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class MarkTaskDoneCommandTest {
    @Test
    public void executeMarkTaskDoneCommand_validTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        taskList.addTask(new ToDo("description"));
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(1);
        Response<Task> markTaskDoneResponse = markTaskDoneCommand.execute(taskList, storage);

        assertEquals(ResponseType.TASK, markTaskDoneResponse.getResponseType());
        assertEquals("awesome!! bobo marked this task as done ~(˘▾˘~)",
                markTaskDoneResponse.getResponseMessage());
        assertEquals(true, markTaskDoneResponse.getResponseObject().isCompleted());
    }
}
