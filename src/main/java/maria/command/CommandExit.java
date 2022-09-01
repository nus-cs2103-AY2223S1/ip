package maria.command;

import maria.TaskManager;

/**
 * Represents the command to exit the application.
 */
public class CommandExit extends Command {

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The program will exit before returning anything
     */
    @Override
    public String execute(TaskManager taskManager) {
        System.exit(0);
        return ""; // Will never be run anyway
    }
}
