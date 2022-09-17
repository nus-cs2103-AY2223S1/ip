import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.MarkTaskNotDoneCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class MarkTaskNotDoneCommandTest {
    @Test
    public void executeMarkTaskNotDoneCommand_validTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        ToDo toDo = new ToDo("description");
        toDo.markTaskAsDone();
        taskList.addTask(toDo);

        MarkTaskNotDoneCommand markTaskDoneCommand = new MarkTaskNotDoneCommand(1);
        Response<Task> markTaskNotDoneResponse = markTaskDoneCommand.execute(taskList, storage);

        assertEquals(ResponseType.TASK, markTaskNotDoneResponse.getResponseType());
        assertEquals("alright-y, bobo marked this task as not done yet  (・◡・)ゝ",
                markTaskNotDoneResponse.getResponseMessage());
        assertEquals(false, markTaskNotDoneResponse.getResponseObject().isCompleted());
    }
}
