package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Task;

/**
 * TODO: Add JavaDocs
 */
public class MarkTaskCommand implements Command {
    public static final String COMMAND_WORD = "mark";

    private static final String MESSAGE_MARK_TASK_AS_DONE = "Nice! I've marked this task as done:";

    private static final String ERROR_MISSING_TASK_INDEX = "You are missing a task number!\n"
            + "Use the 'list' command to view the tasks and their number.";
    private static final String ERROR_NAN_TASK_NUMBER = "The task number you provided is not a number!";

    private final String arguments;

    /**
     * TODO: Add JavaDocs
     */
    public MarkTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
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
        uiManager.print(String.format("%s\n\t%s", MarkTaskCommand.MESSAGE_MARK_TASK_AS_DONE, updatedTask));
    }
}
