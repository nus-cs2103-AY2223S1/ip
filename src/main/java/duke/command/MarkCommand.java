package duke.command;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Task;
import duke.task.TasksList;

/**
 * Represents a command to mark a task in the TasksList as done.
 */
public class MarkCommand extends Command {
    private static final String MARK_MSG = "Nice! I've marked this task as done:\n";
    private String[] inputArray;
    private TasksList tasksList;

    /**
     * Creates a MarkCommand instance.
     * @param tasksList The TasksList containing the task to be marked.
     * @param inputArray The array that represents the user input.
     */
    public MarkCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Mark the task in the TasksList as done.
     * @return The Response to be displayed upon the execution of the command.
     * @throws DukeException If the task cannot be marked as done successfully.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number!");
        }
        try {
            int taskNumber = Integer.parseInt(this.inputArray[1]);
            Task markedTask = this.tasksList.markAsDone(taskNumber);
            return new Response(MarkCommand.MARK_MSG + markedTask);
        // exception due to parsing
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a integer for task number!");
        }
    }
}



