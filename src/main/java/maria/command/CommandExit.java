package maria.command;

import maria.TaskManager;

/**
 * Represents the command to exit the application.
 */
public class CommandExit extends Command {

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {
        System.exit(0);
    }
}
