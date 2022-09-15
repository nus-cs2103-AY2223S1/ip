package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * An abstract class Command which has the execute method and isExit functionality.
 */
public abstract class Command {
    /* A boolean determining whether to exit the program. */
    protected boolean isExit;

    /**
     * Creates a Command object.
     *
     * @param isExit
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks The tasks object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a boolean of whether the Command object isExit is true.
     * @return A boolean specifying whether the Command object isExit is true.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
