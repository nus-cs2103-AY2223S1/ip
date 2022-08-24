package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param ui Ui to interact with the user.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(t);
    }
}
