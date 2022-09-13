package duke;

/**
 * Represent command that user can input.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Returns String to be shown on GUI after the command is executed.
     *
     * @param tasks list of existing tasks.
     * @param ui user interface to be shown.
     * @param storage to rewrite the data file.
     * @return string to be show on GUI
     * @throws DukeException if something went wrong with the update of data file.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Sets isExit instance variable.
     *
     * @param isExit the boolean that you want to set isExit to.
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns isExit instance variable.
     *
     * @return the class variable isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
