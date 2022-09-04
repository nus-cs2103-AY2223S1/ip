package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to mark a task in the task list as not done.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private int t;

    /**
     * Constructs an Unmark Command with the task to be marked
     * as not done.
     *
     * @param t Index of the task to be unmarked.
     */
    public UnmarkCommand(int t) {
        this.t = t;
    }

    /**
     * {@inheritDoc}
     * This command marks the specified task in the task
     * list as not done yet.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.unmark(t);
    }

}
