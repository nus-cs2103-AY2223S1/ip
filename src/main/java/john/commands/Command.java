package john.commands;

import john.data.TaskList;
import john.ui.Ui;

/**
 * Represents a executable command.
 */
public abstract class Command {
    protected TaskList tasklist;
    protected Ui ui;

    /**
     * Sets the tasklist to execute the command on.
     *
     * @param tasklist The tasklist to execute the command on.
     * @param ui The UI to display the command from.
     */
    public void setData(TaskList tasklist, Ui ui) {
        this.tasklist = tasklist;
        this.ui = ui;
    }

    /**
     * Executes a command.
     *
     * @return A string representing the result of the command.
     */
    public abstract String execute();
}
