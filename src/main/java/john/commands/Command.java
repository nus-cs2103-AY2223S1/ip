package john.commands;

import john.data.TaskList;

/**
 * Represents a executable command.
 */
public abstract class Command {
    protected TaskList tasklist;

    /**
     * Sets the tasklist to execute the command on.
     * @param tasklist The tasklist to execute the command on.
     */
    public void setData(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Executes a command.
     * @return A string representing the result of the command.
     */
    public abstract String execute();
}
