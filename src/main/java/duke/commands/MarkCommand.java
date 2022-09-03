package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.undo.MarkUndo;
import duke.undo.UndoAction;

/**
 * Represents a mark command in the Duke application.
 */
public class MarkCommand extends Command {
    /** Command word of the mark command. */
    public static final String COMMAND_WORD = "mark";
    private static final String USER_MESSAGE_FORMAT = "Marked task %d as done!\n  %s";
    private final int index;

    /**
     * Constructor for a mark command that takes in arguments.
     *
     * @param arguments Arguments string is to be of the format "N".
     */
    public MarkCommand(String arguments) {
        index = Integer.parseInt(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute() throws DukeException {
        assert tasks != null : "Should setData() before calling execute().";
        // Check if index is out of bounds.
        if (index <= 0 || index > tasks.size()) {
            throw DukeException.INVALID_INDEX;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.getTask(index - 1);
        UndoAction undoAction = new MarkUndo(task, true);
        task.markAsDone();
        String userMessage = String.format(USER_MESSAGE_FORMAT, index, task);
        return new CommandResult(userMessage, CommandResult.Action.UPDATE_FILE, undoAction);
    }
}
