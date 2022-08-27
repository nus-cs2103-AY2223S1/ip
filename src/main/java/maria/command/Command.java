package maria.command;

import maria.TaskManager;

public abstract class Command {

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    public abstract void execute(TaskManager taskManager);

}
