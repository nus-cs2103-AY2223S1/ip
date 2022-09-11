package maria.command;

import maria.TaskManager;

/**
 * Displays a string that tells the user that the parser cannot understand the command.
 */
public class CommandUnknown extends Command {

    public CommandUnknown() {}

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {
        return "Unknown command. You can click 'Help' to see the commands.";
    }
}
