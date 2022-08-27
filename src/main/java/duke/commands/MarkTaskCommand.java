package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Task;

/**
 * Encapsulate a command for marking a {@link Task} as completed. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code mark {taskNumber}}: To mark the task corresponding to the task number as completed.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class MarkTaskCommand implements Command {
    public static final String COMMAND_WORD = "mark";

    private static final String MESSAGE_MARK_TASK_AS_DONE = "Nice! I've marked this task as done:";

    private static final String ERROR_MISSING_TASK_INDEX = "You are missing a task number!\n"
            + "Use the 'list' command to view the tasks and their number.";
    private static final String ERROR_NAN_TASK_NUMBER = "The task number you provided is not a number!";

    private final String arguments;

    /**
     * Creates a new instance of a Command handler for marking a task as completed.
     *
     * @param arguments The arguments following the {@code 'mark'} prefix supplied by the user from keyboard input
     */
    public MarkTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        // Retrieve the task index (1-indexed) to mark the task as done
        if (this.arguments.length() == 0) {
            throw new DukeException(MarkTaskCommand.ERROR_MISSING_TASK_INDEX);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(MarkTaskCommand.ERROR_NAN_TASK_NUMBER);
        }

        Task task = taskManager.get(taskNumber);
        task.markAsDone();
        Task updatedTask = taskManager.update(taskNumber, task);
        return String.format("%s\n\t%s", MarkTaskCommand.MESSAGE_MARK_TASK_AS_DONE, updatedTask);
    }
}
