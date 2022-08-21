package commands;

import exceptions.DukeException;
import managers.TaskManager;
import models.task.Task;

public class UnmarkTaskCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String MARK_TASK_AS_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String MISSING_TASK_INDEX_ERROR = "You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_NUMBER_ERROR = "The task number you provided is not a number!";
    private static final String TASK_NUMBER_IS_INVALID_ERROR = "The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";

    private final String arguments;

    public UnmarkTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        // Retrieve the task index (1-indexed) to mark the task as undone
        if (this.arguments.length() == 0) {
            throw new DukeException(UnmarkTaskCommand.MISSING_TASK_INDEX_ERROR);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(UnmarkTaskCommand.NAN_TASK_NUMBER_ERROR);
        }

        try {
            Task task = taskManager.get(taskNumber);
            task.markAsUndone();
            Task updatedTask = taskManager.update(taskNumber, task);
            return String.format("%s\n\t%s", UnmarkTaskCommand.MARK_TASK_AS_UNDONE_MESSAGE, updatedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(UnmarkTaskCommand.TASK_NUMBER_IS_INVALID_ERROR);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
