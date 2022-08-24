package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Task;

/**
 * TODO: Add JavaDocs
 */
public class DeleteTaskCommand implements Command {
    public static final String COMMAND_WORD = "delete";

    private static final String MESSAGE_DELETE_TASK = "Noted. I've removed this task:";

    private static final String ERROR_MISSING_TASK_INDEX = "You are missing a task number!\n"
            + "Use the 'list' command to view the tasks and their number.";
    private static final String ERROR_NAN_TASK_NUMBER = "The task number you provided is not a number!";
    private static final String ERROR_TASK_NUMBER_IS_INVALID = "The task number you provided is not valid!\n"
            + "Use the 'list' command to view the tasks and their number.";

    private final String arguments;

    /**
     * TODO: Add JavaDocs
     */
    public DeleteTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        // Retrieve the task index (1-indexed) to delete the task
        if (this.arguments.length() == 0) {
            throw new DukeException(DeleteTaskCommand.ERROR_MISSING_TASK_INDEX);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(DeleteTaskCommand.ERROR_NAN_TASK_NUMBER);
        }

        Task task;
        try {
            task = taskManager.delete(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DeleteTaskCommand.ERROR_TASK_NUMBER_IS_INVALID);
        }

        uiManager.print(String.format(
                "%s\n\t%s\n%s",
                DeleteTaskCommand.MESSAGE_DELETE_TASK,
                task,
                taskManager.getStatus()
        ));
    }
}
