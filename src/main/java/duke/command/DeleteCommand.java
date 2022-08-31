package duke.command;

import duke.exception.DukeException;
import duke.response.Response;
import duke.task.Task;
import duke.task.TasksList;

/**
 * Represents a command to delete a Task from the TasksList.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_MSG = "Noted. I've removed this task:\n";
    private TasksList tasksList;
    private String[] inputArray;

    /**
     * Creates a new DeleteCommand instance.
     * @param tasksList The TasksList to delete the Task from.
     * @param inputArray The array that represents the user input.
     */
    public DeleteCommand(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    /**
     * Deletes the Task from the TasksList.
     * @return The Response to be displayed upon the execution of the command.
     * @throws DukeException If the task cannot be deleted from the TasksList.
     */
    @Override
    public Response execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("Missing Task Number!");
        }
        try {
            int taskNumber = Integer.parseInt(this.inputArray[1]);
            Task deletedTask = this.tasksList.deleteTask(taskNumber);
            StringBuilder sb = new StringBuilder();
            sb.append(DeleteCommand.DELETE_MSG + deletedTask + "\n" + "Now you have ");
            if (this.tasksList.getLength() <= 1) {
                sb.append(this.tasksList.getLength() + " task in the list.\n");
            } else {
                sb.append(this.tasksList.getLength() + " tasks in the list.\n");
            }
            return new Response(sb.toString());
            // exception due to parsing
        } catch (NumberFormatException exception) {
            throw new DukeException("Please enter a integer for task number!");
        }

    }



}
