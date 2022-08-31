package duke.command;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Task;
import duke.task.TasksList;

/**
 * Represents a command to mark a task in the TasksList as undone.
 */
public class UnmarkCommand extends Command {
    private static final String UNMARK_MSG = "Sure! I've marked this task as not done yet:\n";
    private String[] inputArray;
    private TasksList tasksList;

    /**
     * Creates a UnmarkCommand instance.
     * @param tasksList The TasksList containing the task to be unmarked.
     * @param inputArray The array that represents the user input.
     */
    public UnmarkCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Mark the task in the TasksList as undone.
     * @return The Response to be displayed upon the execution of the command.
     * @throws DukeException If the task cannot be marked as undone successfully.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number!");
        }
        try {
            int taskNumber = Integer.parseInt(this.inputArray[1]);
            Task deletedTask = this.tasksList.markAsUndone(taskNumber);
            return new Response(UnmarkCommand.UNMARK_MSG + deletedTask);
            // exception due to parsing
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a integer for task number!");
        }
    }
}



