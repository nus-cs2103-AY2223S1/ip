package commands;

import tasklist.TaskList;

/**
 * Represents an abstract Command that should be inherited by
 * Command subclasses. Should override the abstract methods to
 * provide behavior expected of a Command class.
 */
abstract public class Command {
    protected boolean exit = false;

    /**
     * When the Command is executed, it performs its intended actions.
     * For example, executing an EventCommand adds an Event to the
     * task list and prints relevant information to the chat GUI.
     *
     * @param taskList The taskList relevant to the command.
     * @return A string to print to the chat GUI.
     */
    public abstract String execute(TaskList taskList);

    /**
     * Returns true if the command signals the breaking of the loop
     * in Duke.
     *
     * @return Boolean value representing an exit signal for the loop in Duke.
     */
    public boolean isExit() {
        return this.exit;
    }
}
