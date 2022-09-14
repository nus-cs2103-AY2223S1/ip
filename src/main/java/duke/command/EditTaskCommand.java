package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that edits an existing task.
 */
public class EditTaskCommand extends Command {
    private static final String EDITED_SUCCESSFULLY_MESSAGE = "gotcha! bobo edited this task, ta-da!\n"
        + " * * (•◡•) / * *";
    private int taskNumber;
    private String userEditInput;

    /**
     * Constructs an edit task command that will edit the task associated
     * to the specified task number with the specified details.
     *
     * @param userInput The command input by the user, without the edit command.
     */
    public EditTaskCommand(String userInput) {
        int taskNumberEndIndex = extractTaskNumberEnd(userInput);
        String taskNumberString = userInput.substring(0, taskNumberEndIndex);
        taskNumber = Integer.parseInt(taskNumberString);
        userEditInput = userInput.substring(taskNumberEndIndex);
    }

    private int extractTaskNumberEnd(String userInput) {
        int endIndex = 0;
        while (Character.isDigit(userInput.charAt(endIndex))) {
            endIndex++;
        }
        return endIndex;
    }

    @Override
    public Response<Task> execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNumber);
        Task editedTask = task.edit(userEditInput);
        return new Response<>(ResponseType.TASK, EDITED_SUCCESSFULLY_MESSAGE, editedTask);
    }
}
