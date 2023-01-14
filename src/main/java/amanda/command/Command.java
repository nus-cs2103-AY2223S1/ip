package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;

/**
 * Command that represent a command inputted by user.
 */
public abstract class Command {

    /**
     * Executes the command.
     */
    public abstract void execute(TaskList tasks, StoreManager store);
}
