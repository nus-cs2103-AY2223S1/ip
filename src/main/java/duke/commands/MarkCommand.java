package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task in the
 * task list as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private int t;

    /**
     * Constructs a Mark Command with the task to be marked.
     *
     * @param t Index of the task to be marked.
     */
    public MarkCommand(int t) {
        this.t = t;
    }

    /**
     * {@inheritDoc}
     * This command marks the specified task in the task list
     * as done.
     *
     * @param tasks Contains the task list.
     * @param ui Ui to interact with the user.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(t);
    }

}
