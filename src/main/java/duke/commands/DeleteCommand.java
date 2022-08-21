package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Represents a delete command in the Duke application.
 */
public class DeleteCommand extends Command {
    /** Command word of the delete command. */
    public static final String COMMAND_WORD = "delete";
    private static final String USER_MESSAGE_FORMAT = "Removing this task!\n  %s\nNow you have %d tasks left.";
    private final int index;

    /**
     * Constructor for a delete command that takes in arguments.
     *
     * @param arguments Arguments string is to be of the format "N".
     */
    public DeleteCommand(String arguments) {
        this.index = Integer.parseInt(arguments);
    }

    /**
     * Executes the command.
     *
     * @return Result of the execution.
     * @throws DukeException Exception that occurred during the execution of the command.
     */
    @Override
    public CommandResult execute() throws DukeException {
        // Check if index is out of bounds.
        if (this.index <= 0 || this.index > this.tasks.size()) {
            throw DukeException.INVALID_INDEX;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = this.tasks.getTask(this.index - 1);
        this.tasks.removeTask(this.index - 1);
        int numberOfTasks = this.tasks.size();
        String userMessage = String.format(USER_MESSAGE_FORMAT, task, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
