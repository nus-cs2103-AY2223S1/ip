package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.Task;

/**
 * Encapsulates a command for deleting a {@link Task}. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code delete {taskNumber}}: To delete a task corresponding to the task number.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
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
     * Creates a new instance of the Command handler for deleting a {@link Task}.
     *
     * @param arguments The arguments following the {@code 'delete'} prefix that is supplied by the user from keyboard
     *                  input, and should denote the corresponding task number
     */
    public DeleteTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
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

        return String.format(
                "%s\n\t%s\n%s",
                DeleteTaskCommand.MESSAGE_DELETE_TASK,
                task,
                taskManager.getStatus()
        );
    }
}
