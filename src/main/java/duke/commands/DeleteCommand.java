package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int t;

    /**
     * Constructs a delete command with the index of the task
     * to be deleted.
     *
     * @param t Index of the task to be deleted.
     */
    public DeleteCommand(int t) {
        this.t = t;
    }

    /**
     * {@inheritDoc}
     * This command deletes the task specified by the user.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.delete(t);
    }
}
