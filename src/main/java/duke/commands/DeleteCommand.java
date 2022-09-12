package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.Task;
import duke.undo.DeleteUndo;
import duke.undo.UndoAction;

/**
 * Represents a delete command in the Duke application.
 */
public class DeleteCommand extends Command {
    /** Command word of the delete command. */
    public static final String COMMAND_WORD = "delete";
    private static final String USER_MESSAGE_FORMAT = "*beeeeep* I've removed the task!\n  %s\n"
            + "Now you have %d tasks left.";
    private final int index;

    /**
     * Constructs for a delete command with arguments.
     *
     * @param arguments Arguments string is to be of the format "N".
     */
    public DeleteCommand(String arguments) throws DukeException {
        try {
            index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
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
        UndoAction undoAction = new DeleteUndo(task);
        tasks.removeTask(index - 1);
        int numberOfTasks = tasks.size();
        String userMessage = String.format(USER_MESSAGE_FORMAT, task, numberOfTasks);
        return new CommandResult(userMessage, CommandResult.Action.UPDATE_FILE, undoAction);
    }
}
