package duke;

/**
 * Represent command that user can input.
 *
 * @author: Jonas Png
 */
public abstract class Command {

    private boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Setter for the class variable isExit.
     *
     * @param isExit the boolean that you want to set isExit to
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Getter for the class variable isExit.
     *
     * @return the class variable isExit
     */
    public boolean isExit() {
        return this.isExit;
    }
}
