package duke.command;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Task;
import duke.task.TasksList;

/**
 * Represents a command to update a task in the TasksList.
 */
public class UpdateCommand extends Command {
    private static final String DELIMITER = " ";
    private static final String UPDATE_MSG = "Sure! I've updated this task:\n";
    private String[] inputArray;
    private TasksList tasksList;

    /**
     * Creates a UpdateCommand instance.
     * @param tasksList The TasksList containing the task to be updated.
     * @param inputArray The array that represents the user input.
     */
    public UpdateCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Update the task in the TasksList.
     * @return The Response to be displayed upon the execution of the command.
     * @throws DukeException If the task cannot be updated successfully.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number / Task to update to!");
        }

        // split again to get task number and task to update to
        String[] splitArray = this.inputArray[1].split(UpdateCommand.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Missing Task Number / Task to update to!");
        }

        try {
            int taskNumber = Integer.parseInt(splitArray[0]);
            String taskString = splitArray[1];
            Task updatedTask = this.tasksList.updateTask(taskNumber, taskString);
            return new Response(UpdateCommand.UPDATE_MSG + updatedTask);
            //exception due to parsing
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a integer for task number!");
        }
    }
}

