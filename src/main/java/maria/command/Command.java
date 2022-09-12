package maria.command;

import maria.TaskManager;

/**
 * Represents the abstract class for all commands.
 *
 * All commands must implement the method {@code execute}.
 */
public abstract class Command {

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    public abstract String execute(TaskManager taskManager);

}
