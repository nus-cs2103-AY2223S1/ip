package commands;

import exceptions.DukeException;
import models.task.Task;
import managers.TaskManager;
import managers.UiManager;

public class DeleteTaskCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";
    private static final String MISSING_TASK_INDEX_ERROR = "You are missing a task number!\n" +
            "Use the 'list' command to view the tasks and their number.";
    private static final String NAN_TASK_NUMBER_ERROR = "The task number you provided is not a number!";
    private static final String TASK_NUMBER_IS_INVALID_ERROR = "The task number you provided is not valid!\n" +
            "Use the 'list' command to view the tasks and their number.";

    private final String arguments;

    public DeleteTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        // Retrieve the task index (1-indexed) to delete the task
        if (this.arguments.length() == 0) {
            throw new DukeException(DeleteTaskCommand.MISSING_TASK_INDEX_ERROR);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(this.arguments);
        } catch (NumberFormatException e) {
            throw new DukeException(DeleteTaskCommand.NAN_TASK_NUMBER_ERROR);
        }

        Task task;
        try {
            task = taskManager.delete(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DeleteTaskCommand.TASK_NUMBER_IS_INVALID_ERROR);
        }

        uiManager.print(String.format(
                "%s\n\t%s\n%s",
                DeleteTaskCommand.DELETE_TASK_MESSAGE,
                task,
                taskManager.getStatus()
        ));
    }
}
