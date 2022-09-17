import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.DeleteTaskCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class DeleteTaskCommandTest {
    @Test
    public void executeDeleteTaskCommand_validCommand_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        ToDo toDo = new ToDo("description");
        taskList.addTask(toDo);

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(1);
        Response<Task> deletedTaskResponse = deleteTaskCommand.execute(taskList, storage);

        assertEquals(ResponseType.TASK, deletedTaskResponse.getResponseType());
        assertEquals("okayy! bobo removed this task (´･ω･`)┐"
                + " you now have 0 tasks in your list", deletedTaskResponse.getResponseMessage());
        assertEquals(toDo, deletedTaskResponse.getResponseObject());
        assertEquals(0, taskList.size());
    }

    @Test
    public void executeDeleteTaskCommand_invalidTaskCommand_success() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");
        ToDo toDo = new ToDo("description");
        taskList.addTask(toDo);
        try {
            DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(3);
            Response<Task> deletedTaskResponse = deleteTaskCommand.execute(taskList, storage);
        } catch (DukeException e) {
            assertEquals("uhoh... bobo can't find this task (・へ・)??", e.getMessage());
        }
    }
}
