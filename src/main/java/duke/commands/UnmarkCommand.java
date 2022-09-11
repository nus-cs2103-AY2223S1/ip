package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.Task;
import duke.undo.MarkUndo;
import duke.undo.UndoAction;

/**
 * Represents an unmark command in the Duke application.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String USER_MESSAGE_FORMAT = "Marked task %d as not done!\n  %s";
    private final int index;

    /**
     * Constructs an unmark command with arguments.
     *
     * @param arguments Arguments string is to be of the format "N".
     */
    public UnmarkCommand(String arguments) {
        index = Integer.parseInt(arguments);
    }

    @Override
    public CommandResult execute() throws DukeException {
        assert tasks != null : "Should setData() before calling execute().";
        // Check if index is out of bounds.
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidIndexException();
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.getTask(index - 1);
        UndoAction undoAction = new MarkUndo(task, false);
        task.markAsUndone();
        String userMessage = String.format(USER_MESSAGE_FORMAT, index, task);
        return new CommandResult(userMessage, CommandResult.Action.UPDATE_FILE, undoAction);
    }
}
