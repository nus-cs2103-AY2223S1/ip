package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * Represents an unmark command in the Duke application.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String USER_MESSAGE_FORMAT = "Marked task %d as not done!\n  %s";
    private final int index;

    /**
     * Constructor for an unmark command that takes in arguments.
     *
     * @param arguments Arguments string is to be of the format "N".
     */
    public UnmarkCommand(String arguments) {
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
        task.markAsUndone();
        String userMessage = String.format(USER_MESSAGE_FORMAT, this.index, task);
        return new CommandResult(userMessage, true, false);
    }
}
