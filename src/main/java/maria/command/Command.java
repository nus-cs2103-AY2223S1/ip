package maria.command;

import maria.TaskManager;

public abstract class Command {

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    public abstract String execute(TaskManager taskManager);

}
