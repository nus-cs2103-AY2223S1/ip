package handlers;

import exceptions.DukeException;
import models.TaskManager;
import models.Task;

public class UnmarkTaskCommand implements DukeCommand {
    private static final String MARK_TASK_AS_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String MISSING_TASK_INDEX_ERROR = "You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_NUMBER_ERROR = "The task number you provided is not a number!";
    private static final String TASK_NUMBER_IS_INVALID_ERROR = "The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        // Retrieve the task index (1-indexed) to mark the task as undone
        if (arguments.length() == 0) {
            throw new DukeException(UnmarkTaskCommand.MISSING_TASK_INDEX_ERROR);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(UnmarkTaskCommand.NAN_TASK_NUMBER_ERROR);
        }

        Task task;
        try {
            task = taskManager.get(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(UnmarkTaskCommand.TASK_NUMBER_IS_INVALID_ERROR);
        }

        task.markAsUndone();
        return String.format("%s\n\t%s", UnmarkTaskCommand.MARK_TASK_AS_UNDONE_MESSAGE, task);
    }
}
