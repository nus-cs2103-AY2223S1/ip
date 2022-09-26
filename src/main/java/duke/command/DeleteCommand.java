package duke.command;

import duke.duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/** Represents the command to delete task that inherits from Command */
public class DeleteCommand extends Command {
    /** Represents the input keyed by the user. */
    private final String userInput;

    /**
     * Represents a DeleteCommand object
     *
     * @param userInput string from the user
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "";

        String taskDetails = userInput.split(" ", 2)[1];
        int taskIndex = Integer.parseInt(taskDetails);
        Task task = taskList.deleteTask(taskIndex);

        message += "Noted. I've removed this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + taskList.getTaskListSize() + " tasks in the list.\n";
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
