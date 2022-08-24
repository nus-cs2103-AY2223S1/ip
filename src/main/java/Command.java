/**
 * Abstract class Command that represents commands on tasks.
 *
 * @author Elgin
 */
public abstract class Command {
    /** Whether the command is an exit command. */
    protected boolean isExit;

    /**
     * Abstract method that executes the command.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks whether command is an Exit Command.
     *
     * @return True if command is exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
