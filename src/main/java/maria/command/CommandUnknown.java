package maria.command;

import maria.TaskManager;

public class CommandUnknown extends Command {

    private int index;

    public CommandUnknown() {

    }

    /**
     * Executes the command.
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {
        System.out.println("Unknown command.");
    }
}
