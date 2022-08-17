package handlers;

import exceptions.DukeException;
import models.Task;
import models.TaskManager;

public class DeleteTaskCommand implements DukeCommand {
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";
    private static final String MISSING_TASK_INDEX_ERROR = "You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_NUMBER_ERROR = "The task number you provided is not a number!";
    private static final String TASK_NUMBER_IS_INVALID_ERROR = "The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";

    @Override
    public String execute(TaskManager taskManager, String arguments) throws DukeException {
        // Retrieve the task index (1-indexed) to delete the task
        if (arguments.length() == 0) {
            throw new DukeException(DeleteTaskCommand.MISSING_TASK_INDEX_ERROR);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(DeleteTaskCommand.NAN_TASK_NUMBER_ERROR);
        }

        Task task;
        try {
            task = taskManager.delete(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DeleteTaskCommand.TASK_NUMBER_IS_INVALID_ERROR);
        }

        return String.format(
                "%s\n\t%s\n%s",
                DeleteTaskCommand.DELETE_TASK_MESSAGE,
                task,
                taskManager.getStatus()
        );
    }
}
