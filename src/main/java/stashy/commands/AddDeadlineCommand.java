package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.Deadline;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to add a Deadline object.
 */
public class AddDeadlineCommand extends Command {
    public static final String KEYWORD = "deadline";
    private Deadline deadline;

    /**
     * Constructor method.
     *
     * @param deadline The deadline to be added
     */
    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a Deadline task class to the task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        tasks.add(this.deadline);
        ui.showIndented("There, we have a new deadline:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.");
    }
}
