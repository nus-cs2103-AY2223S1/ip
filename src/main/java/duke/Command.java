package duke;

/**
 * Abstract Class that is parent class of AddCommand, DeleteCommand,
 * ExitCommand, ListCommand, MarkCommand and UnmarkCommand.
 */
public abstract class Command {

    /**
     * @param tasks TaskList of all Duke tasks.
     * @param ui Ui object for decorative purposes.
     * @param storage Storage of app information.
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @return boolean of whether the app should stop running.
     */
    abstract boolean isExit();
}
