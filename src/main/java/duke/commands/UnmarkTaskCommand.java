package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.Task;

/**
 * Encapsulate a command for marking a {@link Task} as not completed. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code unmark {taskNumber}}: To mark the task corresponding to the task number as not completed.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class UnmarkTaskCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_DESCRIPTION = UnmarkTaskCommand.COMMAND_WORD
            + " <task>: Marks the task corresponding to the provided task number as not done";

    private static final String MESSAGE_MARK_TASK_AS_UNDONE = "OK, I've marked this task as not done yet:";

    private static final String ERROR_MISSING_TASK_INDEX = "You are missing a task number!\n"
            + "Use the 'list' command to view the tasks and their number.";
    private static final String ERROR_NAN_TASK_NUMBER = "The task number you provided is not a number!";

    private final String arguments;

    /**
     * Creates a new instance of a Command handler for marking a task as not completed.
     *
     * @param arguments The arguments following the {@code 'unmark'} prefix supplied by the user from keyboard input
     */
    public UnmarkTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        // Retrieve the task index (1-indexed) to mark the task as undone
        if (this.arguments.length() == 0) {
            throw new DukeException(UnmarkTaskCommand.ERROR_MISSING_TASK_INDEX);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(UnmarkTaskCommand.ERROR_NAN_TASK_NUMBER);
        }

        Task task = taskManager.get(taskNumber);
        task.markAsUndone();
        Task updatedTask = taskManager.update(taskNumber, task);
        return String.format("%s\n\t%s", UnmarkTaskCommand.MESSAGE_MARK_TASK_AS_UNDONE, updatedTask);
    }
}
